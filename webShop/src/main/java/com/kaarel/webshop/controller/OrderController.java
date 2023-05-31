package com.kaarel.webshop.controller;

import com.kaarel.webshop.entity.Order;
import com.kaarel.webshop.entity.Person;
import com.kaarel.webshop.entity.Product;
import com.kaarel.webshop.model.EverypayData;
import com.kaarel.webshop.repository.OrderRepository;
import com.kaarel.webshop.repository.PersonRepository;
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

@RestController
public class OrderController {
    @Autowired
    OrderRepository orderRepository;
    PersonRepository personRepository;


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
    public String payment(@PathVariable Long personId , @RequestBody List<Product> products){
                               //(Product ::getPrice) --saab ka nii kuid siis ei saa juuurde arvutada summat
        double sum =  products.stream().mapToDouble(Product ::getPrice).sum();

//        double sum2 = 0;
//        for (Product p : products){     //see sama mis üleval lambda...
//            sum2 += p.getPrice();
//        }
        Person person = personRepository.findById(personId).get();

        Order order = new Order();
        order.setPaid(false);
        order.setTotalSum(sum);
        order.setCreationDate(new Date());
        order.setProductList(products);
        order.setPerson(person);

        Order dbOrder = orderRepository.save(order);

        RestTemplate restTemplate = new RestTemplate();

        String url = "https://igw-demo.every-pay.com/api/v4/payments/oneoff";

        EverypayData data = new EverypayData();
        data.setApi_username("e36eb40asf5ec87fa2");
        data.setAccount_name("EUR3D1");
        data.setAmount(sum);
        data.setOrder_reference(dbOrder.getId().toString());
        data.setNonce("a9b7f7a" + dbOrder.getId() + new Date()); //Et genereerida uus nonce
        data.setTimestamp(ZonedDateTime.now().toString());
        data.setCustomer_url("https://maksmine.web.app/makse");

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION,"Basic ZTM2ZWI0MGY1ZWM4N2ZhMjo3YjkxYTNiOWUxYjc0NTI0YzJlOWZjMjgyZjhhYzhjZA==");
        HttpEntity<EverypayData> httpEntity = new HttpEntity<>(data,headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST,httpEntity,String.class); 

        //KODUS: JSON KUJUL STRINGI ASEMEL     EverypayResponse
        // https://json2csharp.com/code-converters/json-to-pojo


//        orderRepository.save(new Order(1l,false,sum,new Date(),products,new Person()));
        return response.getBody();
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
