package com.example.testproject.controller;

import com.example.testproject.common.Constants.ExceptionClass;
import com.example.testproject.common.exception.TestHubException;
import com.example.testproject.data.dto.ProductDto;
import com.example.testproject.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product-api")
@RequiredArgsConstructor
public class ProductController {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;

    // http://localhost:8080/api/v1/product-api/product/{productId}
    @GetMapping("/product/{productId}")
    public ProductDto getProduct(@PathVariable String productId) {
        long startTime = System.currentTimeMillis();
        LOGGER.info("[ProductController] perform {} of testproject API", "getProduct");

        ProductDto productDto = productService.getProduct(productId);

        LOGGER.info("[ProductController] Response :: productId = {}, productName = {}, productPrice = {}, productStock = {}, Response Time = {}ms",
                productDto.getId(), productDto.getName(), productDto.getPrice(), productDto.getStock(), (System.currentTimeMillis() - startTime));
        return productDto;
    }

    // http://localhost:8080/api/v1/product-api/product
    @PostMapping("/product")
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto) {

        // Validation Code Example
        if (productDto.getId().equals("") || productDto.getId().isEmpty()) {
            LOGGER.error("[createProduct] failed Response :: productId is Empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(productDto);
        }

        String productId = productDto.getId();
        String productName = productDto.getName();
        int productPrice = productDto.getPrice();
        int productStock = productDto.getStock();

        ProductDto response = productService.saveProduct(productId, productName, productPrice, productStock);

        LOGGER.info("[createProduct] Response >> productId : {}, productName : {}, productPrice : {}, productStock : {}",
                response.getId(), response.getName(), response.getPrice(), response.getStock());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // http://localhost:8080/api/v1/product-api/product/{productId}
    @DeleteMapping(value = "/product/{productId}")
    public ProductDto deleteProduct(@PathVariable String productId) {
        return null;
    }

    @PostMapping("/product/exception")
    public void exceptionTest() throws TestHubException {
        throw new TestHubException(ExceptionClass.PRODUCT, HttpStatus.BAD_REQUEST, "의도한 에러 발생");
    }
}
