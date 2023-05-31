package com.kaarel.webshop.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
public class EverypayData {

    private String api_username;
    private String account_name;
    private double amount;
    private String order_reference;
    private String nonce;
    private String timestamp;
    private String customer_url;

    }


