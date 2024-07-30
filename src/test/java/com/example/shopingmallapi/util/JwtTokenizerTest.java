package com.example.shopingmallapi.util;

import com.example.shopingmallapi.security.jwt.util.JwtTokenizer;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class JwtTokenizerTest {

    @Autowired
    JwtTokenizer jwtTokenizer;

    @Value("${jwt.secretKey}")
    String accessSecret; // 12345678901234567890123456789012

    public final Long ACCESS_TOKEN_EXPIRE_COUNT = 30 * 60 * 1000L; // 30 * 60s * 1000ms => 30분

    @Test
    public void createToken() {
        String email = "test@example.com";
        List<String> roles = List.of("ROLE_USER"); // ["ROLE_USER"]
        Long id = 1L;
        Claims claims = Jwts.claims().setSubject(email); // JWT 토큰의 payload에 들어갈 내용(claims)을 설정
        // claims - sub    - email
        //        - roles  - ["ROLE_USER"]
        //        - userId - 1L
        claims.put("roles", roles);
        claims.put("userId", id);

        byte[] accessSecret = this.accessSecret.getBytes(StandardCharsets.UTF_8);

        // JWT 생성
        String JwtToken = Jwts.builder() // builder는 JwtBuilder를 반환
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + this.ACCESS_TOKEN_EXPIRE_COUNT))
                .signWith(Keys.hmacShaKeyFor(accessSecret))
                .compact();

        System.out.println("JwtToken = " + JwtToken);
    }

    @Test
    public void verifyToken() {
        byte[] accessSecret = this.accessSecret.getBytes(StandardCharsets.UTF_8);
        // createToken()에서 JwtToken 값
        String jwtToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0QGV4YW1wbGUuY29tIiwicm9sZXMiOlsiUk9MRV9VU0VSIl0sInVzZXJJZCI6MSwiaWF0IjoxNzIyMzM3Nzc0LCJleHAiOjE3MjIzMzk1NzR9._R6ahaLaFcrAVc959vaEeYYIDJLGjiF0WamYJBMXgbc";

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(accessSecret))
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();

        System.out.println("claims.getSubject() = " + claims.getSubject());
        System.out.println("roles = " + claims.get("roles"));
        System.out.println("roles = " + claims.get("userId"));
        System.out.println("roles = " + claims.getIssuedAt());
        System.out.println("roles = " + claims.getExpiration());
    }
}
