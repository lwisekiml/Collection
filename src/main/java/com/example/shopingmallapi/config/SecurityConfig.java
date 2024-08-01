package com.example.shopingmallapi.config;

import com.example.shopingmallapi.security.jwt.exception.CustomAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsUtils;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationManagerConfig authenticationManagerConfig;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;


    // JWT 토큰으로 인증하는 방식으로 사용할거라 인증에서 HttpSession 사용X
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().disable() // id, password를 입력받아 JWT 토큰을 리턴하는 API 만들 것이다.
                .csrf().disable() // CSRF는 Cross Site Request Forgery의 약자. CSRF공격을 막기 위한 방법
                .cors()
                .and()
                .apply(authenticationManagerConfig)
                .and()
                .httpBasic().disable()
                .authorizeRequests() // 인증이 왔을 때 어떻게 처리할 것인가
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll() // Preflight 요청은 허용(사전요청)
                .mvcMatchers("/members/signup", "/members/login", "/users/refresh").permitAll() // "/signup", "/login", "/users/refresh" 페이지는 권한 유무에 관계없이 다 호출 가능
                .mvcMatchers(GET, "/**").hasAnyRole("USER", "ADMIN") // 나머지 GET, POST 방식은 "USER", "MANAGER", "ADMIN" 권한이 있어야 호출 가능
                .mvcMatchers(POST, "/**").hasAnyRole("USER", "ADMIN")
                .anyRequest().hasAnyRole("USER", "ADMIN")
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(customAuthenticationEntryPoint) // exception이 발생 했을 경우 customAuthenticationEntryPoint 에 있는 것으로 처리
                .and()
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
