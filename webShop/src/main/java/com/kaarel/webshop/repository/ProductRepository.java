package com.kaarel.webshop.repository;

import com.kaarel.webshop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    //Hinnna järjekorras
    List<Product> findAllByOrderByPrice();

    //Kõige kallim toode
    Product findFirstByOrderByPriceDesc();

    //Kõik aktiivsed ja kogus suurem kui 0 toodet
    List<Product> findAllByActiveTrueAndStockGreaterThan(int stock);

    //Kõik aktiivsed
    List<Product> findAllByActive(boolean active);

    List<Product> findAllByOrderByIdDesc();

}
