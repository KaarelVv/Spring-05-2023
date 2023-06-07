package com.kaarel.webshop.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.kaarel.webshop.entity.Product;
import com.kaarel.webshop.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
@Log4j2
@Component //
public class ProductCache {
    @Autowired
    ProductRepository productRepository;

   private LoadingCache<Long, Product> productLoadingCache = CacheBuilder
           .newBuilder()
           .expireAfterWrite(1, TimeUnit.MINUTES)
           .build(new CacheLoader<Long, Product>() {
               @Override
               public Product load(Long aLong) throws Exception {
                   log.info("Võtsin cache andbesasist");
                   return productRepository.findById(aLong).get();
               }
           });
    public Product getProduct(Long id) throws ExecutionException {
        log.info("Võtan toodet");
        return productLoadingCache.get(id);
    }

    public void updateProduct(Long id){
        productLoadingCache.refresh(id);
    }
    public void emptyCache(){
        productLoadingCache.invalidateAll();
    }
}
