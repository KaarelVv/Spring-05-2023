package com.kaarel.webshop.service;

import com.kaarel.webshop.cache.ProductCache;
import com.kaarel.webshop.entity.Order;
import com.kaarel.webshop.entity.Person;
import com.kaarel.webshop.entity.Product;
import com.kaarel.webshop.model.EveryPayResponse;
import com.kaarel.webshop.model.EverypayData;
import com.kaarel.webshop.model.EverypayLink;
import com.kaarel.webshop.model.EverypayPaymentState;
import com.kaarel.webshop.repository.OrderRepository;
import com.kaarel.webshop.repository.PersonRepository;
import com.kaarel.webshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.random.RandomGenerator;

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
    @Value("${everypay.url}") //vaata application properties
    String everypayUrl;
    @Value("${everypay.authorization}") //vaata application properties
    String everypayAuhorization;
    @Value("${everypay.username}") //vaata application properties
    String everypayUsername;
    @Value("${everypay.customer-url}") //vaata application properties
    String everypayCustomerUrl;

    public Order saveOrderToDb(Long personId, List<Product> products, double sum) throws Exception {
        Person person ;
        if (personRepository.findById(personId).isPresent()) {
            person = personRepository.findById(personId).get();
        }else {
            throw new Exception("Person not found");
        }
        Order order = new Order();
        order.setPaid("initial");
        order.setTotalSum(sum);
        order.setCreationDate(new Date());
        order.setProductList(products);
        order.setPerson(person);

        Order dbOrder = orderRepository.save(order);
        return dbOrder;
    }

    public EverypayLink getEverypayLink(double sum, Order dbOrder) {
        EverypayData data = getEverypayData(sum, dbOrder);

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION,everypayAuhorization);
        HttpEntity<EverypayData> httpEntity = new HttpEntity<>(data,headers);

        String url = everypayUrl + "/payments/oneoff";
        ResponseEntity<EveryPayResponse> response = restTemplate.exchange(url, HttpMethod.POST,httpEntity,EveryPayResponse.class);


        EverypayLink everypayLink = new EverypayLink();

        everypayLink.setLink(response.getBody().payment_link);
        return everypayLink;
    }


    private  EverypayData getEverypayData(double sum, Order dbOrder) {
        EverypayData data = new EverypayData();
        data.setApi_username(everypayUsername);
        data.setAccount_name("EUR3D1");
        data.setAmount(sum);
        data.setOrder_reference(dbOrder.getId().toString());
        data.setNonce("a9b7f7a" + dbOrder.getId() + new Date()); //Et genereerida uus nonce
        data.setTimestamp(ZonedDateTime.now().toString());
        data.setCustomer_url(everypayCustomerUrl);
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

    public Order checkIfOrderPaid(String paymentReference) {

        String username = everypayUsername;
        String url = everypayUrl + paymentReference + "?api_username=" + username;

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION,everypayAuhorization);
        HttpEntity httpEntity = new HttpEntity<>(headers);

        ResponseEntity<EverypayPaymentState> response = restTemplate.exchange(url,HttpMethod.GET,httpEntity, EverypayPaymentState.class);

        EverypayPaymentState httpBody = response.getBody();
        Order order = orderRepository.findById(Long.valueOf(httpBody.order_reference)).get();
        String orderStatus = httpBody.payment_state;

        order.setPaid(orderStatus);
        orderRepository.save(order);


        return order ;
    }
}
