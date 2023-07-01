package com.kaarel.webshop.repository;


import com.kaarel.webshop.entity.OrderRow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRowRepository extends JpaRepository<OrderRow,Long> {
}
