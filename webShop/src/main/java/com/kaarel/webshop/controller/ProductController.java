package com.kaarel.webshop.controller;

import com.kaarel.webshop.cache.ProductCache;
import com.kaarel.webshop.entity.Product;
import com.kaarel.webshop.repository.ProductRepository;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
@CrossOrigin("http://localhost:3000")
@RestController
public class ProductController {

    @Autowired
    ProductCache productCache;

    @Autowired
    ProductRepository productRepository;



    //GET localhost:8080/product
    @GetMapping("product")
    public List<Product> getProducts(){
        return productRepository.findAllByOrderByIdDesc();// findallByID
    }

    @GetMapping ("product/{id}")
    public Product getProduct(@PathVariable Long id) throws ExecutionException {
       return productCache.getProduct(id);
    }

    @DeleteMapping ("product/delete/{id}")
    public List<Product> deleteProducts(@PathVariable Long id){
        productRepository.deleteById(id);
        productCache.emptyCache(); // tühjendab vahemälu
        return productRepository.findAll();
    }

    //POST localhost:8080/product
    @PostMapping("product")
    public List<Product> addProducts(@RequestBody Product product){
        if (product.getId()==null ||!productRepository.existsById(product.getId())) {
            productRepository.save(product);
        }
        return productRepository.findAll();
    }

    //POST localhost:8080/product
    @PutMapping("product/edit")
    public List<Product> editProducts(@RequestBody Product product){
        if (productRepository.existsById(product.getId())) {
            productRepository.save(product);
            productCache.updateProduct(product.getId());
        }
        return productRepository.findAll();
    }

    //POST localhost:8080/product/{id}
    @PatchMapping("decrease-stock/{id}")
    public List<Product> decreaseStock(@PathVariable Long id){
        Product product = productRepository.findById(id).get();
        if (product.getStock() > 0){
        product.setStock(product.getStock() - 1);
        productRepository.save(product);
        productCache.updateProduct(product.getId());
        }
        return productRepository.findAllByOrderByIdDesc();
    }

    //POST localhost:8080/product/{id}
    @PatchMapping("increase-stock/{id}")
    public List<Product> increaseStock(@PathVariable Long id){
        Product product = productRepository.findById(id).get();
        product.setStock(product.getStock() + 1);
        productRepository.save(product);
        productCache.updateProduct(product.getId());
        return productRepository.findAllByOrderByIdDesc();
    }

    //GET localhost:8080/product
    @GetMapping("product-by-price")
    public List<Product> getProductByPrice(){
        return productRepository.findAllByOrderByPrice();
    }

    //GET localhost;8080/product
    @GetMapping("products-by-highest-price")
    public List<Product> getProductsDescending(){
        return Collections.singletonList(productRepository.findFirstByOrderByPriceDesc());
    }

    //GET localhost:8080/product
    @GetMapping("active-product")
    public List<Product> getActiveProducts(){
        return productRepository.findAllByActive(true);
    }

    //GET localhost:8080/public-products
    @GetMapping("public-products")
    public List<Product> getActiveProductsWithStock(){
        return productRepository.findAllByActiveTrueAndStockGreaterThan(0);
    }

    @DeleteMapping("delete/{id}")
    public List<Product> deleteProduct(@PathVariable Long id){
        productRepository.deleteById(id);
        return productRepository.findAll();
    }

}
