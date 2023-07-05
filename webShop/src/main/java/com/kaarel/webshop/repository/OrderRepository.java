package com.kaarel.webshop.repository;

import com.kaarel.webshop.entity.Order;
import com.kaarel.webshop.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findAllByPerson(Person person);
}
