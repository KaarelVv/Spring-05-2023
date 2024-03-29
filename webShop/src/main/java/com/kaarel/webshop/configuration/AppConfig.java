package com.kaarel.webshop.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig {
    @Bean // tekitab mällu modelMapper ja kui autowired siis ei tekita iga kord uut kohta
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean // tekitab mällu BCryptPassword ja kui autowired siis ei tekita iga kord uut kohta
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
//    @Bean
//    public RestTemplate restTemplate(){
//        return new RestTemplate();
//    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) { //välaminevad
        //Siia saan kirjutada mingit loogikat restemplate osas

        return new RestTemplateBuilder().build();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
//                WebMvcConfigurer.super.addCorsMappings(registry);
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000")
                        .allowedMethods("GET", "POST", "DELETE", "PUT", "PATCH")
                        .allowedHeaders("*");
//                registry.addMapping("/public")
//                        .allowedOrigins("http://localhost:4200") //Angular port
//                        .allowedMethods("GET");
            }
        };
    }
}
