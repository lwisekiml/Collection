package com.example.testproject.data.dto;

import com.example.testproject.data.entity.Product;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ProductDto {
    @NotNull
    private String id;

    @NotNull
    private String name;

    @NotNull
    @Min(value = 500)
    @Max(value = 300000)
    private int price;

    @NotNull
    @Min(value = 0)
    @Max(value = 9999)
    private int stock;

    public Product toEntity(){
        return Product.builder()
                .id(id)
                .name(name)
                .price(price)
                .stock(stock)
                .build();
    }
}
