package com.example.shopingmallapi.security.jwt.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER) // 메소드 파라미터에서 사용됨
@Retention(RetentionPolicy.RUNTIME) // 클래스가 실행될 때
public @interface IfLogin {
}
