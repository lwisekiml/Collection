package com.example.shopingmallapi.service;

import com.example.shopingmallapi.domain.Member;
import com.example.shopingmallapi.domain.Role;
import com.example.shopingmallapi.repository.MemberRepository;
import com.example.shopingmallapi.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final RoleRepository roleRepository;

    @Transactional(readOnly = true)
    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다."));
    }

    @Transactional
    public Member addMember(Member member) {
        Optional<Role> userRole = roleRepository.findByName("ROLE_USER");
        member.addRole(userRole.get());
        return memberRepository.save(member);
    }

    @Transactional(readOnly = true)
    public Optional<Member> getMember(Long memberId){
        return memberRepository.findById(memberId);
    }

    @Transactional(readOnly = true)
    public Optional<Member> getMember(String email){
        return memberRepository.findByEmail(email);
    }
}
