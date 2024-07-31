package com.example.shopingmallapi.controller;

import com.example.shopingmallapi.domain.Member;
import com.example.shopingmallapi.domain.RefreshToken;
import com.example.shopingmallapi.domain.Role;
import com.example.shopingmallapi.dto.MemberLoginDto;
import com.example.shopingmallapi.dto.MemberLoginResponseDto;
import com.example.shopingmallapi.dto.MemberSignupDto;
import com.example.shopingmallapi.dto.MemberSignupResponseDto;
import com.example.shopingmallapi.security.jwt.util.JwtTokenizer;
import com.example.shopingmallapi.service.MemberService;
import com.example.shopingmallapi.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Validated
public class MemberController {

    private final JwtTokenizer jwtTokenizer;
    private final MemberService memberService;
    private final RefreshTokenService refreshTokenService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody @Valid MemberSignupDto memberSignupDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        Member member = new Member();
        member.setName(memberSignupDto.getName());
        member.setEmail(memberSignupDto.getEmail());
        member.setPassword(passwordEncoder.encode(memberSignupDto.getPassword()));

        Member saveMember = memberService.addMember(member);

        MemberSignupResponseDto memberSignupResponse = new MemberSignupResponseDto();
        memberSignupResponse.setMemberId(saveMember.getMemberId());
        memberSignupResponse.setName(saveMember.getName());
        memberSignupResponse.setRegdate(saveMember.getRegdate());
        memberSignupResponse.setEmail(saveMember.getEmail());

        // 회원가입
        return new ResponseEntity(memberSignupResponse, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid MemberLoginDto loginDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        // email이 없을 경우 Exception 발생. Global Exception 처리 필요
        Member member = memberService.findByEmail(loginDto.getEmail());
        if (!passwordEncoder.matches(loginDto.getPassword(), member.getPassword())) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        List<String> roles = member.getRoles().stream().map(Role::getName).collect(Collectors.toList());

        // JWT토큰을 생성하였다. jwt라이브러리를 이용하여 생성.
        String accessToken = jwtTokenizer.createAccessToken(member.getMemberId(), member.getEmail(), roles);
        String refreshToken = jwtTokenizer.createRefreshToken(member.getMemberId(), member.getEmail(), roles);

        // RefreshToken은 성능때문에 Redis에 저장하는 것이 좋다.
        RefreshToken refreshTokenEntity = new RefreshToken();
        refreshTokenEntity.setValue(refreshToken);
        refreshTokenEntity.setMemberId(member.getMemberId());
        refreshTokenService.addRefreshToken(refreshTokenEntity);

        MemberLoginResponseDto loginResponse = MemberLoginResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .memberId(member.getMemberId())
                .nickname(member.getName())
                .build();
        return new ResponseEntity(loginResponse, HttpStatus.OK);
    }

    @DeleteMapping("/logout")
    public ResponseEntity logout(@RequestHeader("Authorization") String token) {
        // token repository에서 refresh Token에 해당하는 값을 삭제한다.
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
