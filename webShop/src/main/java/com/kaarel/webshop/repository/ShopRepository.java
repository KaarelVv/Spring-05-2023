package com.kaarel.webshop.repository;

import com.kaarel.webshop.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, Long> {
}
