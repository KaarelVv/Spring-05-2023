package com.kaarel.webshop.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    @Bean // tekitab mällu modelMapper ja kui autowired siis ei tekita iga kord uut kohta
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
//    @Bean
//    public RestTemplate restTemplate(){
//        return new RestTemplate();
//    }
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder){
        //Siia saan kirjutada mingit loogikat restemplate osas

        return new RestTemplateBuilder().build();
    }
}
