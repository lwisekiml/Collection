package com.example.testproject.data.dao.impl;

import com.example.testproject.data.dao.ProductDAO;
import com.example.testproject.data.entity.Product;
import com.example.testproject.data.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductDAOImpl implements ProductDAO {

    private final ProductRepository productRepository;

    @Override
    public Product saveProduct(Product product) {
        productRepository.save(product);
        return product;
    }

    @Override
    public Product getProduct(String productId) {
        return productRepository.findById(productId).get();
    }
}
