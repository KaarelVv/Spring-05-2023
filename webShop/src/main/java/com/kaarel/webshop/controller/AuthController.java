package com.kaarel.webshop.controller;

import com.kaarel.webshop.entity.Person;
import com.kaarel.webshop.model.AuthToken;
import com.kaarel.webshop.model.LoginData;
import com.kaarel.webshop.repository.PersonRepository;
import com.kaarel.webshop.security.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


@RestController
public class AuthController {
    @Autowired
    PersonRepository personRepository;
    @Autowired
    BCryptPasswordEncoder encoder;
    @Autowired
    TokenGenerator tokenGenerator;

    @PostMapping("login")
    public ResponseEntity<AuthToken> login(@RequestBody LoginData loginData) {

        Person person = personRepository.findPersonByEmail(loginData.getEmail());
        AuthToken authToken = new AuthToken();

//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (encoder.matches(loginData.getPassword(), person.getPassword())) {
            authToken.setToken(tokenGenerator.generateToken(person.getEmail(), person.isAdmin()));
        }
        return ResponseEntity.ok().body(authToken);
    }

    @PostMapping("signup")
    public ResponseEntity<AuthToken> signup(@RequestBody Person person) throws Exception {
        AuthToken authToken = new AuthToken();
        if (person.getId() == null || !personRepository.existsById(person.getId())) {
//            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String hashedPassword = encoder.encode(person.getPassword());
            person.setPassword(hashedPassword);
            person.setCreationDate(new Date());
            personRepository.save(person);
            authToken.setToken(tokenGenerator.generateToken(person.getEmail(), false));
        } else {
            throw new Exception("Id juba olemas");
        }

        return ResponseEntity.ok().body(authToken);
    }
}
