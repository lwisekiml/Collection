package com.example.shopingmallapi.controller;

import com.example.shopingmallapi.domain.Cart;
import com.example.shopingmallapi.domain.Member;
import com.example.shopingmallapi.dto.LoginUserDto;
import com.example.shopingmallapi.security.jwt.util.IfLogin;
import com.example.shopingmallapi.service.CartService;
import com.example.shopingmallapi.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartApiController {
    private final CartService cartService;
    private final MemberService memberService;

    @PostMapping // class IfLoginArgumentResolver -> supportsParameter() 가 실행된다.
    public Cart addCart(@IfLogin LoginUserDto loginUserDto) {
        System.out.println("loginUserDto.getEmail() = " + loginUserDto.getEmail());
        Optional<Member> member = memberService.getMember(loginUserDto.getEmail());
        if (member.isEmpty()) {
            throw new IllegalArgumentException("해당 사용자가 없습니다.");
        }

        LocalDate localDate = LocalDate.now();
        String date = localDate.getYear() + (localDate.getMonthValue() < 10 ? "0":"") + localDate.getMonthValue() + (localDate.getDayOfMonth() < 10 ? "0":"") + localDate.getDayOfMonth();
        return cartService.addCart(member.get().getMemberId(), date);
    }
}
