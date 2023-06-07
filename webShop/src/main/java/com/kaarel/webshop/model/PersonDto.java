package com.kaarel.webshop.model;

import lombok.Data;

@Data
public class PersonDto {//Dto-> datatransferobject. Tahan tagastada ainult valitud

    private String firstName;
    private String lastName;
    private String email;
}
