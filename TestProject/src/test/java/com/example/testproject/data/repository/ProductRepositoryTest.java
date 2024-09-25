package com.example.testproject.data.repository;

import com.example.testproject.data.entity.Product;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @BeforeEach
    void GenerateData() {
        int count = 1;
        productRepository.save(getProduct(Integer.toString(count), count++, 2000, 3000)); // 1
        productRepository.save(getProduct(Integer.toString(count), count++, 3000, 9000)); // 2
        productRepository.save(getProduct(Integer.toString(--count), count = count + 2, 1500, 200)); // 2
        productRepository.save(getProduct(Integer.toString(count), count++, 4000, 5000)); // 4
        productRepository.save(getProduct(Integer.toString(count), count++, 10000, 1500)); // 5
        productRepository.save(getProduct(Integer.toString(count), count++, 1000, 1000)); // 6
        productRepository.save(getProduct(Integer.toString(count), count++, 500, 10000)); // 7
        productRepository.save(getProduct(Integer.toString(count), count++, 8500, 3500)); // 8
        productRepository.save(getProduct(Integer.toString(count), count++, 7200, 2000)); // 9
        productRepository.save(getProduct(Integer.toString(count), count++, 5100, 1700)); // 10
    }

    private Product getProduct(String id, int nameNumber, int price, int stock) {
        return new Product(id, "상품" + nameNumber, price, stock);
    }

    @Test
    void findTest() {
        List<Product> foundAll = productRepository.findAll();
        System.out.println("====↓↓ Test Data ↓↓====");
        for (Product product : foundAll) {
            System.out.println(product.toString());
        }
        System.out.println("====↑↑ Test Data ↑↑====");

        List<Product> foundEntities = productRepository.findByName("상품4");
        for (Product product : foundEntities) {
            System.out.println(product.toString());
        }

        List<Product> queryEntities = productRepository.queryByName("상품4");
        for (Product product : queryEntities) {
            System.out.println(product.toString());
        }
    }

    @Test
    void existTest() {
        List<Product> foundAll = productRepository.findAll();
        System.out.println("====↓↓ Test Data ↓↓====");
        for (Product product : foundAll) {
            System.out.println(product.toString());
        }
        System.out.println("====↑↑ Test Data ↑↑====");

        System.out.println(productRepository.existsByName("상품4"));
        System.out.println(productRepository.existsByName("상품2"));
    }

    @Test
    void countTest() {
        List<Product> foundAll = productRepository.findAll();
        System.out.println("====↓↓ Test Data ↓↓====");
        for(Product product : foundAll){
            System.out.println(product.toString());
        }
        System.out.println("====↑↑ Test Data ↑↑====");

        System.out.println(productRepository.countByName("상품4"));
    }

    @Test
    @Transactional
    void deleteTest() {
        System.out.println("before : " + productRepository.count());

        productRepository.deleteByName("상품1");
        productRepository.removeByName("상품9");

        System.out.println("After : " + productRepository.count());
    }

    @Test
    void topTest(){
        productRepository.save(getProduct("109", 123, 1500, 5000));
        productRepository.save(getProduct("101", 123, 2500, 5000));
        productRepository.save(getProduct("102", 123, 3500, 5000));
        productRepository.save(getProduct("103", 123, 4500, 5000));
        productRepository.save(getProduct("104", 123, 1000, 5000));
        productRepository.save(getProduct("105", 123, 2000, 5000));
        productRepository.save(getProduct("106", 123, 3000, 5000));
        productRepository.save(getProduct("107", 123, 4000, 5000));

        List<Product> foundEntities = productRepository.findFirst5ByName("상품123");
        for(Product product : foundEntities){
            System.out.println(product.toString());
        }

        List<Product> foundEntities2 = productRepository.findTop3ByName("상품123");
        for(Product product : foundEntities2){
            System.out.println(product.toString());
        }
    }

    /* ↓↓ 조건자 키워드 테스트 ↓↓ */

    @Test
    void isEqualsTest() {
        List<Product> foundAll = productRepository.findAll();
        System.out.println("====↓↓ Test Data ↓↓====");
        for(Product product : foundAll){
            System.out.println(product.toString());
        }
        System.out.println("====↑↑ Test Data ↑↑====");

        System.out.println(productRepository.findByIdIs("1"));
        System.out.println(productRepository.findByIdEquals("1"));
    }

    @Test
    void notTest() {
        List<Product> foundAll = productRepository.findAll();
        System.out.println("====↓↓ Test Data ↓↓====");
        for(Product product : foundAll){
            System.out.println(product.toString());
        }
        System.out.println("====↑↑ Test Data ↑↑====");

        List<Product> foundEntities = productRepository.findByIdNot("1");
        for(Product product : foundEntities){
            System.out.println(product);
        }
        System.out.println(productRepository.findByIdNot("1"));
        System.out.println(productRepository.findByIdIsNot("1"));
    }

    @Test
    void nullTest() {
        List<Product> foundAll = productRepository.findAll();
        System.out.println("====↓↓ Test Data ↓↓====");
        for(Product product : foundAll){
            System.out.println(product.toString());
        }
        System.out.println("====↑↑ Test Data ↑↑====");

        System.out.println(productRepository.findByStockIsNull());
        System.out.println(productRepository.findByStockIsNotNull());
    }

    @Test
    void andTest() {
        List<Product> foundAll = productRepository.findAll();
        System.out.println("====↓↓ Test Data ↓↓====");
        for(Product product : foundAll){
            System.out.println(product.toString());
        }
        System.out.println("====↑↑ Test Data ↑↑====");

        System.out.println(productRepository.findTopByIdAndName("1", "상품1"));
    }

    @Test
    void greaterTest() {
        List<Product> foundAll = productRepository.findAll();
        System.out.println("====↓↓ Test Data ↓↓====");
        for(Product product : foundAll){
            System.out.println(product.toString());
        }
        System.out.println("====↑↑ Test Data ↑↑====");

        List<Product> productEntities = productRepository.findByPriceGreaterThan(5000);

        for(Product product : productEntities){
            System.out.println(product);
        }
    }

    @Test
    void containTest() {
        List<Product> foundAll = productRepository.findAll();
        System.out.println("====↓↓ Test Data ↓↓====");
        for(Product product : foundAll){
            System.out.println(product.toString());
        }
        System.out.println("====↑↑ Test Data ↑↑====");

        System.out.println(productRepository.findByNameContaining("상품1"));
    }

    @Test
    void orderByTest() {
        productRepository.save(new Product("14", "나는아니야", 111111, 222222));

        List<Product> foundAll = productRepository.findAll();
        System.out.println("====↓↓ Test Data ↓↓====");
        for (Product product : foundAll) {
            System.out.println(product.toString());
        }
        System.out.println("====↑↑ Test Data ↑↑====");

        List<Product> foundProducts = productRepository.findByNameContainingOrderByStockAsc("상품"); // "상품"이 포함된
        for(Product product : foundProducts){
            System.out.println(product);
        }

        foundProducts = productRepository.findByNameContainingOrderByStockDesc("상품");
        for(Product product : foundProducts){
            System.out.println(product);
        }
    }

    @Test
    void multiOrderByTest() {
        productRepository.save(new Product("15", "상품15", 1500, 33333));

        List<Product> foundAll = productRepository.findAll();
        System.out.println("====↓↓ Test Data ↓↓====");
        for (Product product : foundAll) {
            System.out.println(product.toString());
        }
        System.out.println("====↑↑ Test Data ↑↑====");

        // 같은 가격이면 stock으로 내림차순
        List<Product> foundProducts = productRepository.findByNameContainingOrderByPriceAscStockDesc("상품");
        for(Product product : foundProducts){
            System.out.println(product);
        }
    }

    @Test
    void orderByWithParameterTest() {
        productRepository.save(new Product("15", "상품15", 1500, 33333));

        List<Product> foundAll = productRepository.findAll();
        System.out.println("====↓↓ Test Data ↓↓====");
        for (Product product : foundAll) {
            System.out.println(product.toString());
        }
        System.out.println("====↑↑ Test Data ↑↑====");

        List<Product> foundProducts = productRepository.findByNameContaining("상품", Sort.by(Sort.Order.asc("price")));
        for(Product product : foundProducts){
            System.out.println(product);
        }

        foundProducts = productRepository.findByNameContaining("상품", Sort.by(Sort.Order.asc("price"), Sort.Order.asc("stock")));
        for(Product product : foundProducts){
            System.out.println(product);
        }
    }

    @Test
    void pagingTest() {
        List<Product> foundAll = productRepository.findAll();
        System.out.println("====↓↓ Test Data ↓↓====");
        for (Product product : foundAll) {
            System.out.println(product.toString());
        }
        System.out.println("====↑↑ Test Data ↑↑====");

        // 200 초과, 0 page, 2개 씩
        List<Product> foundProducts = productRepository.findByPriceGreaterThan(200, PageRequest.of(0, 2));
        for(Product product : foundProducts){
            System.out.println(product);
        }

        foundProducts = productRepository.findByPriceGreaterThan(200, PageRequest.of(1, 2));
        for(Product product : foundProducts){
            System.out.println(product);
        }

        foundProducts = productRepository.findByPriceGreaterThan(200, PageRequest.of(2, 2));
        for(Product product : foundProducts){
            System.out.println(product);
        }
    }

    @Test
    public void queryTest() {
        List<Product> foundAll = productRepository.findAll();
        System.out.println("====↓↓ Test Data ↓↓====");
        for (Product product : foundAll) {
            System.out.println(product.toString());
        }
        System.out.println("====↑↑ Test Data ↑↑====");

        List<Product> foundProducts = productRepository.findByPriceBasis();
        for (Product product : foundProducts) {
            System.out.println(product);
        }
    }

    @Test
    public void nativeQueryTest() {
        List<Product> foundAll = productRepository.findAll();
        System.out.println("====↓↓ Test Data ↓↓====");
        for (Product product : foundAll) {
            System.out.println(product.toString());
        }
        System.out.println("====↑↑ Test Data ↑↑====");

        List<Product> foundProducts = productRepository.findByPriceBasisNativeQuery();
        for (Product product : foundProducts) {
            System.out.println(product);
        }
    }

    @Test
    public void parameterQueryTest() {
        List<Product> foundAll = productRepository.findAll();
        System.out.println("====↓↓ Test Data ↓↓====");
        for (Product product : foundAll) {
            System.out.println(product.toString());
        }
        System.out.println("====↑↑ Test Data ↑↑====");

        List<Product> foundProducts = productRepository.findByPriceWithParameter(2000);
        for (Product product : foundProducts) {
            System.out.println(product);
        }
    }

    @Test
    public void parameterNamingQueryTest() {
        List<Product> foundAll = productRepository.findAll();
        System.out.println("====↓↓ Test Data ↓↓====");
        for (Product product : foundAll) {
            System.out.println(product.toString());
        }
        System.out.println("====↑↑ Test Data ↑↑====");

        List<Product> foundProducts = productRepository.findByPriceWithParameterNaming(2000);
        for (Product product : foundProducts) {
            System.out.println(product);
        }
    }

    @Test
    public void parameterNamingQueryTest2() {
        List<Product> foundAll = productRepository.findAll();
        System.out.println("====↓↓ Test Data ↓↓====");
        for (Product product : foundAll) {
            System.out.println(product.toString());
        }
        System.out.println("====↑↑ Test Data ↑↑====");

        List<Product> foundProducts = productRepository.findByPriceWithParameterNaming2(2000);
        for (Product product : foundProducts) {
            System.out.println(product);
        }
    }

    @Test
    public void nativeQueryPagingTest() {
        List<Product> foundAll = productRepository.findAll();
        System.out.println("====↓↓ Test Data ↓↓====");
        for (Product product : foundAll) {
            System.out.println(product.toString());
        }
        System.out.println("====↑↑ Test Data ↑↑====");

        List<Product> foundProducts = productRepository.findByPriceWithParameterPaging(2000, PageRequest.of(2, 2));
        for (Product product : foundProducts) {
            System.out.println(product);
        }
    }
}