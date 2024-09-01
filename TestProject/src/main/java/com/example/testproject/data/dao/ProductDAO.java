package com.example.testproject.data.dao;

import com.example.testproject.data.entity.Product;

public interface ProductDAO {
    Product saveProduct(Product product);
    Product getProduct(String productId);
}
