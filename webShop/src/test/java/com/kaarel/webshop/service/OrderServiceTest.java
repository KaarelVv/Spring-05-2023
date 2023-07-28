package com.kaarel.webshop.service;

import com.kaarel.webshop.cache.ProductCache;
import com.kaarel.webshop.entity.OrderRow;
import com.kaarel.webshop.entity.Product;
import com.kaarel.webshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class OrderServiceTest {
    Product product = new Product();
    OrderRow orderRow = new OrderRow(1L,2,product);
    OrderRow orderRow1 = new OrderRow(1L,2,product);

    @Mock
    ProductRepository productRepository;
    @Mock
    ProductCache productCache;
    OrderService orderService = new OrderService();
    @BeforeEach
    void setUp() {
        product.setId(1L);
        product.setPrice(1.99);
        orderService.productRepository = productRepository;
        orderService.productCache = productCache;
        productRepository.save(product);
    }
    @Test
    void saveOrderToDb() {
    }

    @Test
    void getEverypayLink() {
    }

    @Test
    void getDbProducts() throws ExecutionException {
        List<OrderRow> originalProducts = orderService.getDbProducts(Arrays.asList(orderRow,orderRow1));
        assertEquals(2,originalProducts.size());
        assertEquals(1.99,originalProducts.get(0).getProduct().getPrice()); //aga muidu peaks üks assertEquals olema
    }

    @Test
    void checkIfOrderPaid() {
    }

    @Test
    void totalSum() {
        double sum =orderService.totalSum(Collections.singletonList(orderRow));
        assertEquals(3.98,sum);
    }
}