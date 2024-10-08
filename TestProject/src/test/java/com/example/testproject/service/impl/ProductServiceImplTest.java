package com.example.testproject.service.impl;

import com.example.testproject.data.dto.ProductDto;
import com.example.testproject.data.entity.Product;
import com.example.testproject.data.handler.Impl.ProductDataHandlerImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.verify;

//@SpringBootTest(classes = {ProductDataHandlerImpl.class, ProductServiceImpl.class})
@ExtendWith(SpringExtension.class)
@Import({ProductDataHandlerImpl.class, ProductServiceImpl.class})
public class ProductServiceImplTest {

    @MockBean
    ProductDataHandlerImpl productDataHandler;

    @Autowired
    ProductServiceImpl productService;

    @Test
    public void getProductTest() {
        //given
        Mockito.when(productDataHandler.getProductEntity("123"))
                .thenReturn(new Product("123", "pen", 2000, 3000));

        ProductDto productDto = productService.getProduct("123");

        Assertions.assertEquals(productDto.getId(), "123");

        verify(productDataHandler).getProductEntity("123");
        Assertions.assertEquals(productDto.getName(), "pen");
        Assertions.assertEquals(productDto.getPrice(), 2000);
        Assertions.assertEquals(productDto.getStock(), 3000);
    }

    @Test
    public void saveProductTest() {
        //given
        Mockito.when(productDataHandler.saveProductEntity("123", "pen", 2000, 3000))
                .thenReturn(new Product("123", "pen", 2000, 3000));

        ProductDto productDto = productService.saveProduct("123", "pen", 2000, 3000);

        Assertions.assertEquals(productDto.getId(), "123");
        Assertions.assertEquals(productDto.getName(), "pen");
        Assertions.assertEquals(productDto.getPrice(), 2000);
        Assertions.assertEquals(productDto.getStock(), 3000);

        verify(productDataHandler).saveProductEntity("123", "pen", 2000, 3000);
    }

}