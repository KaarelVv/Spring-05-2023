package com.kaarel.webshop.controller;

import com.kaarel.webshop.entity.Order;
import com.kaarel.webshop.entity.Product;
import com.kaarel.webshop.model.EverypayLink;
import com.kaarel.webshop.repository.OrderRepository;
import com.kaarel.webshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderService orderService;


    // võtab kõik
    @GetMapping("order")
    public List<Order> getOrders(){
         return orderRepository.findAll();
    }

    // võtab ühe id järgi
    @GetMapping("order/{id}")
    public Order getOrder(@PathVariable Long id){
        return orderRepository.findById(id).orElseThrow();
    }

    //LISAME ORDER ANDMEBAASI SEL HETKEL KUI MAKSET ALUSTATAKSE
    @PostMapping("payment/{personId}")
    public EverypayLink payment(@PathVariable Long personId , @RequestBody List<Product> products) throws Exception {

        List<Product> originalProduct = orderService.getDbProducts(products);
                               //(Product ::getPrice) --saab ka nii kuid siis ei saa juuurde arvutada summat
        double sum =  originalProduct.stream().mapToDouble(Product ::getPrice).sum(); // võtta igaühe juurest ID ja leida ta andmebaasist
        // cache Google Guava

        Order dbOrder = orderService.saveOrderToDb(personId, originalProduct, sum);

        EverypayLink everypayLink = orderService.getEverypayLink(sum, dbOrder);
        return everypayLink;
    }
    @GetMapping("check-payment/{paymentReference}")
    public Order checkPayment(@PathVariable String paymentReference){

        Order order = orderService.checkIfOrderPaid(paymentReference);

        return order;
    }
    //http://localhost:8080/check-payment/c6d0914ff12c04ab04d384c4cbb46e5977311c636e0aac34ceac837737ce70bc
}
