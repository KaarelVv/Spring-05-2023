package com.kaarel.webshop.service;

import com.kaarel.webshop.cache.ProductCache;
import com.kaarel.webshop.entity.Order;
import com.kaarel.webshop.entity.Person;
import com.kaarel.webshop.entity.Product;
import com.kaarel.webshop.model.EveryPayResponse;
import com.kaarel.webshop.model.EverypayData;
import com.kaarel.webshop.model.EverypayLink;
import com.kaarel.webshop.repository.OrderRepository;
import com.kaarel.webshop.repository.PersonRepository;
import com.kaarel.webshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class OrderService {
    @Autowired
    PersonRepository personRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductCache productCache;

    public Order saveOrderToDb(Long personId, List<Product> products, double sum) {
        Person person = personRepository.findById(personId).get();

        Order order = new Order();
        order.setPaid(false);
        order.setTotalSum(sum);
        order.setCreationDate(new Date());
        order.setProductList(products);
        order.setPerson(person);

        Order dbOrder = orderRepository.save(order);
        return dbOrder;
    }
    public EverypayLink getEverypayLink(double sum, Order dbOrder, String url) {
        EverypayData data = getEverypayData(sum, dbOrder);

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION,"Basic ZTM2ZWI0MGY1ZWM4N2ZhMjo3YjkxYTNiOWUxYjc0NTI0YzJlOWZjMjgyZjhhYzhjZA==");
        HttpEntity<EverypayData> httpEntity = new HttpEntity<>(data,headers);
        ResponseEntity<EveryPayResponse> response = restTemplate.exchange(url, HttpMethod.POST,httpEntity,EveryPayResponse.class);


        EverypayLink everypayLink = new EverypayLink();

        everypayLink.setLink(response.getBody().payment_link);
        return everypayLink;
    }

    private  EverypayData getEverypayData(double sum, Order dbOrder) {
        EverypayData data = new EverypayData();
        data.setApi_username("e36eb40f5ec87fa2");
        data.setAccount_name("EUR3D1");
        data.setAmount(sum);
        data.setOrder_reference(dbOrder.getId().toString());
        data.setNonce("a9b7f7a" + dbOrder.getId() + new Date()); //Et genereerida uus nonce
        data.setTimestamp(ZonedDateTime.now().toString());
        data.setCustomer_url("https://maksmine.web.app/makse");
        return data;
    }

    public List<Product> getDbProducts(List<Product> products) throws ExecutionException {
        List<Product> dbProducts = new ArrayList<>();
        for (Product p:products){
            Product originalProduct = productCache.getProduct(p.getId());
            dbProducts.add(originalProduct);
        }
        return dbProducts;
    }
}
