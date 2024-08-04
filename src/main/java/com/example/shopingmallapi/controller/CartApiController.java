package com.example.shopingmallapi.controller;

import com.example.shopingmallapi.domain.Cart;
import com.example.shopingmallapi.dto.AddCartDto;
import com.example.shopingmallapi.dto.LoginUserDto;
import com.example.shopingmallapi.security.jwt.util.IfLogin;
import com.example.shopingmallapi.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartApiController {
    private final CartService cartService;

    @PostMapping // class IfLoginArgumentResolver -> supportsParameter() 가 실행된다.
    public Cart addCart(@IfLogin LoginUserDto loginUserDto, @RequestBody AddCartDto addCartDto) {
        System.out.println("loginUserDto.getEmail() = " + loginUserDto.getEmail());
        LocalDate localDate = LocalDate.now();
        String date = localDate.getYear() + (localDate.getMonthValue() < 10 ? "0":"") + localDate.getMonthValue() + (localDate.getDayOfMonth() < 10 ? "0":"") + localDate.getDayOfMonth();
        return cartService.addCart(addCartDto.getMemberId(), date);
    }
}
