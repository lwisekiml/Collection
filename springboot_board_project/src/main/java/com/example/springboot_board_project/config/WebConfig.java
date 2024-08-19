package com.example.springboot_board_project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private String resourcePath = "/upload/**"; // view에서 접근할 경로
    private String savePath = "file:///G:/springboot_img/"; // 실제 파일 저장 경로
    // Mac에서는 아래와 같이
//    private String savePath = "file:///Users/사용자이름/springboot_img/";

    // 뷰 화면에서 resourcePath으로 접근하면 savePath 경로에서 찾아준다.
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(resourcePath)
                .addResourceLocations(savePath);
    }
}
