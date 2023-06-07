package com.kaarel.mobileshop.controller;

import com.kaarel.mobileshop.model.MobileShopResponce;
import com.kaarel.mobileshop.model.Product;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

//On mobiiltelefonide edasimüüja, kes palub teil enda telefonide müümise jaoks teha valmis veebipood. Alustame back-endist.
//Telefonide andmebaasi soovitakse enda pool hoida, seega palutakse, et me midagi ENDA andmebaasi sisestama ei hakkaks.
//Nende andmebaasi päringud on meile piiratud – me saame vaid võtta (GET päring).
//Andmebaasi aadress on siin:
//https://dummyjson.com/products
//https://freecodegenerators.com/code-converters/json-to-pojo
//Tee sellest mudel.
//Kui pead määrama tagastuse restTemplate.exchange() sees, siis tee UusKlass.class
//(me tegime NordpoolResponse.class)
//Tee controller võtmiseks.
//Teeme API otspunkti sees RestTemplate abil päringu, HttpEntity jääb tühjaks (null)  ja tagastame Frontendile massiivi mobiiltelefonidest [] abil.
//Võimalda mobiiltelefonide võtmist ka muuta front-endi poolt (anna kaasa mingid kindlad parameetrid)
@RestController
public class MobileShopController {

@GetMapping("mobile")
public List<String> getProductTitles() {
    RestTemplate restTemplate = new RestTemplate();
    String url = "https://dummyjson.com/products";
    ResponseEntity<MobileShopResponce> response = restTemplate.exchange(url, HttpMethod.GET, null, MobileShopResponce.class);
    List<Product> products = response.getBody().getProducts();

    List<String> titles = new ArrayList<>();
    for (Product product : products) {
        titles.add(product.getTitle());
    }

    return titles;
}
}
