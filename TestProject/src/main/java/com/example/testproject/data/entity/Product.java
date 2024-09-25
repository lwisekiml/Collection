package com.example.testproject.data.entity;

import com.example.testproject.data.dto.ProductDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "product")
public class Product extends BaseEntity {
    @Id
    String id;
    String name;
    Integer price;
    Integer stock;

    public ProductDto toDto(){
        return ProductDto.builder()
                .id(id)
                .name(name)
                .price(price)
                .stock(stock)
                .build();
    }
}
