package com.example.samplespringapp;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public void send(String emailAddress, String text){
        System.out.println("Sending email to" + emailAddress);
        System.out.println(text);
        System.out.println("Email was sent");
    }
}
