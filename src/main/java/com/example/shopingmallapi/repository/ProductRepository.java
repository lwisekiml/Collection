package com.example.shopingmallapi.repository;

import com.example.shopingmallapi.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
