package com.example.shopingmallapi.config;

import com.example.shopingmallapi.security.jwt.util.IfLoginArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

// Spring MVC 에 대한 설정파일. 웹에대한 설정파일
@Configuration
public class WebConfig implements WebMvcConfigurer {
    // CORS에 대한 설정. CORS는 Cross Origin Resource Sharing의 약자.
    // 프론트 엔드는 3000번 포트 (React.js), 백 엔드는 8080번 포트
    // http://localhost:3000 ---> 8080 api를 호출할 수 있도록 설정.
    // 즉, 아래 메소드가 없으면 3000번 포트에서 8080포트 api를 호출할 수 없다.
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET", "POST", "PATCH", "PUT", "OPTIONS", "DELETE")
                .allowCredentials(true);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new IfLoginArgumentResolver());
    }
}
