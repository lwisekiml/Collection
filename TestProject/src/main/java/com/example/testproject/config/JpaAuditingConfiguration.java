package com.example.testproject.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing // @EnableJpaAuditing 이 없으면 createdAt, updatedAt 값이 null이 된다.
public class JpaAuditingConfiguration { // 클래스 이름은 아무것으로 해도 상관없다.
}
