package com.kaarel.webshop.controller;

import com.kaarel.webshop.model.OmnivaParcelMachine;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
public class ParcelMachineController {
    //@PathVariable  URL : parcel/
    @GetMapping("parcel-machines/{country}")
    public List<OmnivaParcelMachine> getParcelMachines(@PathVariable String country){

        RestTemplate restTemplate = new RestTemplate(); //Teeb api päringu
        String url = "https://www.omniva.ee/locations.json";
                        //url kuhu   meetodi liik   body millele saadan    mida ma sealt lehelt saan
        ResponseEntity<OmnivaParcelMachine[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET,null,OmnivaParcelMachine[].class);
        //ResponseEntity --->  siia sisse liiguvad tagastatavad andmed, body ,statuskood, header
        List<OmnivaParcelMachine> omnivaPM = Arrays.stream(responseEntity.getBody()).filter(e -> e.a0_NAME.equals("EE")).toList();
        return omnivaPM;
    }
}
