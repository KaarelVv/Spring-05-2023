package com.kaarel.webshop.controller;

import com.kaarel.webshop.entity.Order;
import com.kaarel.webshop.entity.OrderRow;
import com.kaarel.webshop.entity.Person;
import com.kaarel.webshop.model.EverypayLink;
import com.kaarel.webshop.repository.OrderRepository;
import com.kaarel.webshop.repository.PersonRepository;
import com.kaarel.webshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController

public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    OrderService orderService;


    @GetMapping("person-order")
    public List<Order> getPersonOrder() {//getContext üle terve rakenduse
        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Person person = personRepository.findPersonByEmail(email);
        return orderRepository.findAllByPerson(person);
    }

    // võtab kõik
    @GetMapping("order")
    public List<Order> getOrders() {
        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        return orderRepository.findAll();
    }

    // võtab ühe id järgi
    @GetMapping("order/{id}")
    public Order getOrder(@PathVariable Long id) {
        return orderRepository.findById(id).orElseThrow();
    }

    //LISAME ORDER ANDMEBAASI SEL HETKEL KUI MAKSET ALUSTATAKSE
    @PostMapping("payment")
    public EverypayLink payment(@RequestBody List<OrderRow> orderRow) throws Exception {

        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Person person = personRepository.findPersonByEmail(email);

        List<OrderRow> originalProduct = orderService.getDbProducts(orderRow);

        double sum = originalProduct.stream().mapToDouble(e -> e.getProduct().getPrice() * e.getQuantity()).sum();

        // double sum =  originalProduct.stream().mapToDouble(Product ::getPrice).sum(); <- vana süsteemi järgi

        Order dbOrder = orderService.saveOrderToDb(person.getId(), originalProduct, sum);

        return orderService.getEverypayLink(sum, dbOrder);
    }

    @GetMapping("check-payment/{paymentReference}")
    public Order checkPayment(@PathVariable String paymentReference) {
        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        return orderService.checkIfOrderPaid(paymentReference);
    }
    //http://localhost:8080/check-payment/c6d0914ff12c04ab04d384c4cbb46e5977311c636e0aac34ceac837737ce70bc
}
