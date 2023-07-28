package com.carsite.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class TokenGenerator {
    public String generateToken(String email, boolean isAdmin){
        Calendar expiration = Calendar.getInstance();
        expiration.setTime(new Date());
        expiration.add(Calendar.MINUTE,20);
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, "secret")
                .setIssuer("CarCatalogue")
                .setSubject(email)
                .setExpiration(expiration.getTime())
                .setAudience(isAdmin ? "ADMIN":"USER")
                .compact();
    }
}
