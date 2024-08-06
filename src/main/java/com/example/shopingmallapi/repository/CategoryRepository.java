package com.example.shopingmallapi.repository;

import com.example.shopingmallapi.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
