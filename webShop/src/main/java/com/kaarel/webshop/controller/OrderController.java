package com.kaarel.webshop.controller;

import com.kaarel.webshop.entity.Order;
import com.kaarel.webshop.entity.Person;
import com.kaarel.webshop.entity.Product;
import com.kaarel.webshop.model.EveryPayResponse;
import com.kaarel.webshop.model.EverypayData;
import com.kaarel.webshop.model.EverypayLink;
import com.kaarel.webshop.repository.OrderRepository;
import com.kaarel.webshop.repository.PersonRepository;
import com.kaarel.webshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

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
        return orderRepository.findById(id).get();
    }

    //LISAME ORDER ANDMEBAASI SEL HETKEL KUI MAKSET ALUSTATAKSE
    @PostMapping("payment/{personId}")
    public EverypayLink payment(@PathVariable Long personId , @RequestBody List<Product> products) throws ExecutionException {

        List<Product> originalProduct = orderService.getDbProducts(products);
                               //(Product ::getPrice) --saab ka nii kuid siis ei saa juuurde arvutada summat
        double sum =  originalProduct.stream().mapToDouble(Product ::getPrice).sum(); // võtta igaühe juurest ID ja leida ta andmebaasist
        // cache Google Guava

        Order dbOrder = orderService.saveOrderToDb(personId, originalProduct, sum);

        String url = "https://igw-demo.every-pay.com/api/v4/payments/oneoff";

        EverypayLink everypayLink = orderService.getEverypayLink(sum, dbOrder, url);
        return everypayLink;
    }




//    // kustutab id järgi
//    @DeleteMapping("order/delete")
//    public List<Order> deleteOrder(@PathVariable Long id){
//        orderRepository.deleteById(id);
//        return orderRepository.findAll();
//    }
//    // muudab orderi
//    @PutMapping("order/change")
//    public List<Order> editOrder(@PathVariable Order order){
//        if (orderRepository.existsById(order.getId())){
//            orderRepository.save(order);
//        }
//        return orderRepository.findAll();
//    }

}
