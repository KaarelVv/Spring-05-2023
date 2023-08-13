package com.example.samplespringapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SampleSpringAppApplication {

    @Autowired
    private static EmailService emailService;

    public static void main(String[] args) {
        SpringApplication.run(SampleSpringAppApplication.class, args);

        emailService.send("test@email.com","Hello world!");
    }

}
