package com.example.testproject.controller;

import com.example.testproject.dto.MemberDTO;
import com.example.testproject.service.RestTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rest-template")
public class RestTemplateController {

    private final RestTemplateService restTemplateService;

    @GetMapping("/test-hub")
    public String getTestHub() {
        return restTemplateService.getTestHub();
    }

    @GetMapping("/name")
    public String getName() {
        return restTemplateService.getName();
    }

    @GetMapping("/name2")
    public String getName2() {
        return restTemplateService.getName2();
    }

    @PostMapping("/dto")
    public ResponseEntity<MemberDTO> postDto() {
        return restTemplateService.postDto();
    }

    @PostMapping("/add-header")
    public ResponseEntity<MemberDTO> addHeader() {
        return restTemplateService.addHeader();
    }
}
