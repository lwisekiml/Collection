package com.example.testproject.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class TestHubExceptionHandler {

    private final Logger LOGGER = LoggerFactory.getLogger(TestHubExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> ExceptionHandler(Exception e) {
        HttpHeaders responseHeaders = new HttpHeaders();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        LOGGER.info("Advice 내 TestExceptionHandler 호출");

        Map<String, String> map = new HashMap<>();
        map.put("error type", httpStatus.getReasonPhrase());
        map.put("code", "400");
        map.put("message", "에러 발생");

        return new ResponseEntity<>(map, responseHeaders, httpStatus);
    }

    @ExceptionHandler(TestHubException.class)
    public ResponseEntity<Map<String, String>> ExceptionHandler(TestHubException e) {
        HttpHeaders responseHeaders = new HttpHeaders();

        Map<String, String> map = new HashMap<>();
        map.put("error type", e.getHttpStatusType());
        map.put("error code", Integer.toString(e.getHttpStatusCode())); // Map<String, Object>로 설정하면 toString 불필요
        map.put("message", e.getMessage());

        return new ResponseEntity<>(map, responseHeaders, e.getHttpStatus());
    }
}
