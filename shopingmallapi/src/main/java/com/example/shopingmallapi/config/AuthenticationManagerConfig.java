package com.example.shopingmallapi.config;

import com.example.shopingmallapi.security.jwt.filter.JwtAuthenticationFilter;
import com.example.shopingmallapi.security.jwt.provider.JwtAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class AuthenticationManagerConfig extends AbstractHttpConfigurer<AuthenticationManagerConfig, HttpSecurity> {

    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);

        // spring_security.png 에서 빨간 네모 부분(AuthenticationFilter, AuthenticationManager, AuthenticationProvider 만듬)
        builder.addFilterBefore(
                        new JwtAuthenticationFilter(authenticationManager),
                        UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(jwtAuthenticationProvider);
    }
}
