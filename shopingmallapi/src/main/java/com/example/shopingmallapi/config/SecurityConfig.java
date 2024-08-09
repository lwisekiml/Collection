package com.example.shopingmallapi.config;

import com.example.shopingmallapi.security.jwt.exception.CustomAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationManagerConfig authenticationManagerConfig;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;


    // JWT 토큰으로 인증하는 방식으로 사용할거라 인증에서 HttpSession 사용X
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .formLogin(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configurationSource(corsConfigurationSource()))
                .httpBasic(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(httpRequests -> httpRequests
                        .requestMatchers(CorsUtils::isPreFlightRequest).permitAll() // Preflight 요청은 허용한다. https://velog.io/@jijang/%EC%82%AC%EC%A0%84-%EC%9A%94%EC%B2%AD-Preflight-request
                        .requestMatchers("/members/signup", "/members/login", "/members/refreshToken").permitAll()
                        .requestMatchers(GET, "/categories/**", "/products/**").permitAll()
                        .requestMatchers(GET, "/**").hasAnyRole("USER")
                        .requestMatchers(POST, "/**").hasAnyRole("USER", "ADMIN")
                        .anyRequest().hasAnyRole("USER", "ADMIN"))
                .exceptionHandling(httpSecurityExceptionHandlingConfigurer -> httpSecurityExceptionHandlingConfigurer.authenticationEntryPoint(customAuthenticationEntryPoint))
                .with(authenticationManagerConfig, Customizer.withDefaults());

        return httpSecurity.build();
    }

    // <<Advanced>> Security Cors로 변경 시도
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);
        config.setAllowedOrigins(List.of("http://localhost:3000"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setExposedHeaders(List.of("*"));

        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
