package com.example.shopingmallapi.service;

import com.example.shopingmallapi.domain.Category;
import com.example.shopingmallapi.dto.AddCategoryDto;
import com.example.shopingmallapi.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public Category addCategory(AddCategoryDto addCategoryDto){
        Category category = new Category();
        category.setName(addCategoryDto.getName());

        return categoryRepository.save(category);
    }

    @Transactional(readOnly = true)
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Category getCategory(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow();
    }
}
