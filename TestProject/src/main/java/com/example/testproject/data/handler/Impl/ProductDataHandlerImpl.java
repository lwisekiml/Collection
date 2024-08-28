package com.example.testproject.data.handler.Impl;

import com.example.testproject.data.dao.ProductDAO;
import com.example.testproject.data.entity.ProductEntity;
import com.example.testproject.data.handler.ProductDataHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductDataHandlerImpl implements ProductDataHandler {

    private final ProductDAO productDAO;

    @Override
    public ProductEntity saveProductEntity(String productId, String productName, int productPrice, int productStock) {
        ProductEntity productEntity = new ProductEntity(productId, productName, productPrice, productStock);
        return productDAO.saveProduct(productEntity);
    }

    @Override
    public ProductEntity getProductEntity(String productId) {
        return productDAO.getProduct(productId);
    }
}
