package com.example.shopingmallapi.controller;

import com.example.shopingmallapi.domain.Category;
import com.example.shopingmallapi.dto.AddCategoryDto;
import com.example.shopingmallapi.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Category addCategory(@RequestBody AddCategoryDto addCategoryDto){
        return categoryService.addCategory(addCategoryDto);
    }

    @GetMapping
    public List<Category> getAllCategories(){
        return categoryService.getCategories();
    }
}
