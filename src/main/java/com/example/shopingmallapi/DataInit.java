package com.example.shopingmallapi;

import com.example.shopingmallapi.domain.Category;
import com.example.shopingmallapi.domain.Product;
import com.example.shopingmallapi.domain.Rating;
import com.example.shopingmallapi.domain.Role;
import com.example.shopingmallapi.repository.CategoryRepository;
import com.example.shopingmallapi.repository.ProductRepository;
import com.example.shopingmallapi.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class DataInit {

    private final RoleRepository roleRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @PostConstruct
    public void init() {
        roleRepository.save(new Role("ROLE_USER"));
        roleRepository.save(new Role("ROLE_ADMIN"));

        categoryRepository.save(new Category(1L, "상의"));
        categoryRepository.save(new Category(2L, "하의"));
        categoryRepository.save(new Category(3L, "아우터"));
        categoryRepository.save(new Category(4L, "신발"));
        categoryRepository.save(new Category(5L, "가방"));
        categoryRepository.save(new Category(6L, "악세서리"));
        categoryRepository.save(new Category(7L, "언더웨어"));
        categoryRepository.save(new Category(8L, "수영복"));
        categoryRepository.save(new Category(9L, "잠옷"));
        categoryRepository.save(new Category(10L,"스포츠웨어"));

        productRepository.save(new Product(1L, "린넨 셔츠", 20000.0, "시원한 린넨 소재의 셔츠", categoryRepository.findById(1L).get(), "https://via.placeholder.com/400x600", new Rating(4.0, 50)));
        productRepository.save(new Product(2L, "면 스랙스", 25000.0, "면 소재의 슬랙스", categoryRepository.findById(2L).get(), "https://via.placeholder.com/400x600", new Rating(4.5, 60)));
        productRepository.save(new Product(3L, "가디건", 40000.0, "편안한 가디건", categoryRepository.findById(3L).get(), "https://via.placeholder.com/400x600", new Rating(4.0, 40)));
        productRepository.save(new Product(4L, "로퍼", 80000.0, "멋스러운 로퍼", categoryRepository.findById(4L).get(), "https://via.placeholder.com/400x600", new Rating(4.6, 70)));
    }
}
