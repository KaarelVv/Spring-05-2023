package com.kaarel.webshop.security;

import com.kaarel.webshop.model.AuthToken;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

@Component
public class TokenGenerator {
    public String generateToken(){


        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, "super-secret-key")
                .setIssuer("Webshop")
                .compact();



    }
}
