package com.example.shopingmallapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rating {
    private Double rate;
    private Integer count;
}
