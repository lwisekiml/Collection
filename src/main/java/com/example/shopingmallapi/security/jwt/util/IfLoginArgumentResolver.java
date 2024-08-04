package com.example.shopingmallapi.security.jwt.util;

import com.example.shopingmallapi.dto.LoginUserDto;
import com.example.shopingmallapi.security.jwt.token.JwtAuthenticationToken;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Collection;
import java.util.Iterator;

public class IfLoginArgumentResolver implements HandlerMethodArgumentResolver {

    @Override // true가 return 되면 resolveArgument 실행
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(IfLogin.class) != null
                && parameter.getParameterType() == LoginUserDto.class;
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory) throws Exception {
        Authentication authentication = null;
        try {
            authentication = SecurityContextHolder.getContext().getAuthentication();
        } catch (Exception e) {
            return null;
        }
        // 아직 login을 안한 상태이면 addCart의 loginUserDto 값이 null이 된다.
        if (authentication == null) {
            return null;
        }

        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;
        LoginUserDto loginUserDto = new LoginUserDto();
        Object principal = jwtAuthenticationToken.getPrincipal(); // email
        if (principal == null) {
            return null;
        }
        loginUserDto.setEmail((String) principal);
        System.out.println("loginUserDto.getEmail() = " + loginUserDto.getEmail());

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        while (iterator.hasNext()) {
            GrantedAuthority grantedAuthority = iterator.next();
            String role = grantedAuthority.getAuthority();
            System.out.println("role = " + role);
            loginUserDto.addRole(role);
        }

        return loginUserDto; // addCart의 loginUserDto 값
    }
}
