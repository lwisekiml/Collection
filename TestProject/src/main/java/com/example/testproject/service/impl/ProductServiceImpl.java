package com.example.testproject.service.impl;

import com.example.testproject.data.dto.ProductDto;
import com.example.testproject.data.entity.Product;
import com.example.testproject.data.handler.ProductDataHandler;
import com.example.testproject.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductDataHandler productDataHandler;

    @Override
    public ProductDto saveProduct(String productId, String productName, int productPrice, int productStock) {
        Product product = productDataHandler.saveProductEntity(productId, productName, productPrice, productStock);
        return new ProductDto(product.getId(), product.getName(), product.getPrice(), product.getStock());
    }

    @Override
    public ProductDto getProduct(String productId) {
        Product product = productDataHandler.getProductEntity(productId);
        return new ProductDto(product.getId(), product.getName(), product.getPrice(), product.getStock());
    }
}
