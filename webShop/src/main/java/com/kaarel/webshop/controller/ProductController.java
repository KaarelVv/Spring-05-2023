package com.kaarel.webshop.controller;

import com.kaarel.webshop.cache.ProductCache;
import com.kaarel.webshop.entity.Product;
import com.kaarel.webshop.repository.CategoryRepository;
import com.kaarel.webshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;


@RestController
public class ProductController {

    @Autowired
    ProductCache productCache;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;


    //GET localhost:8080/product
    @GetMapping("product")
    public List<Product> getProducts() {
        return productRepository.findAllByOrderByIdDesc();// findallByID
    }

    @GetMapping("product/{id}")
    public Product getProduct(@PathVariable Long id) throws ExecutionException {
        return productCache.getProduct(id);
    }

    @DeleteMapping("product/{id}")
    public ResponseEntity<List<Product>> deleteProducts(@PathVariable Long id) {
        if(!productRepository.existsById(id)){
            return ResponseEntity.status(204).body(productRepository.findAll());
        }
        productRepository.deleteById(id);
        productCache.emptyCache(); // tühjendab vahemälu
        return ResponseEntity.ok(productRepository.findAll()); //Front-endile
    }

    //POST localhost:8080/product
    @PostMapping("product")
    public List<Product> addProducts(@RequestBody Product product) {
        if (product.getId() == null || !productRepository.existsById(product.getId())) {
            productRepository.save(product);
        }
        return productRepository.findAll();
    }


    //POST localhost:8080/product
    @PutMapping("product")
    public List<Product> editProducts(@RequestBody Product product) {
        if (productRepository.existsById(product.getId())) {
            productRepository.save(product);
            productCache.updateProduct(product.getId());
        }
        return productRepository.findAll();
    }
//    @PutMapping("product/edit-all")
//    public List<Product> editProductsAndCategory(@RequestBody Product product) {
//        if (productRepository.existsById(product.getId())) {
//            Category category = product.getCategory();
//            if (category != null) {
//                Category existingCategory = categoryRepository.findByName(category.getName());
//                if (existingCategory != null) {
//                    product.setCategory(existingCategory);
//                }
//            }
//            productRepository.save(product);
//            productCache.updateProduct(product.getId());
//        }
//        return productRepository.findAll();
//    }

    //POST localhost:8080/product/{id}
    @PatchMapping("decrease-stock/{id}")
    public List<Product> decreaseStock(@PathVariable Long id) {
        Product product = productRepository.findById(id).get();
        if (product.getStock() > 0) {
            product.setStock(product.getStock() - 1);
            productRepository.save(product);
            productCache.updateProduct(product.getId());
        }
        return productRepository.findAllByOrderByIdDesc();
    }

    //POST localhost:8080/product/{id}
    @PatchMapping("increase-stock/{id}")
    public List<Product> increaseStock(@PathVariable Long id) {
        Product product = productRepository.findById(id).get();
        product.setStock(product.getStock() + 1);
        productRepository.save(product);
        productCache.updateProduct(product.getId());
        return productRepository.findAllByOrderByIdDesc();
    }

    //GET localhost:8080/product
    @GetMapping("product-by-price")
    public List<Product> getProductByPrice() {
        return productRepository.findAllByOrderByPrice();
    }

    //GET localhost:8080/product
    @GetMapping("products-by-highest-price")
    public List<Product> getProductsDescending() {
        return Collections.singletonList(productRepository.findFirstByOrderByPriceDesc());
    }

    //GET localhost:8080/product
    @GetMapping("active-product")
    public List<Product> getActiveProducts() {
        return productRepository.findAllByActive(true);
    }

    //GET localhost:8080/public-products
    @GetMapping("public-products")
    public List<Product> getActiveProductsWithStock() {
        return productRepository.findAllByActiveTrueAndStockGreaterThan(0);
    }


}
