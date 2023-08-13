package com.carsite.controller;

import com.carsite.entity.Account;
import com.carsite.exception.EmailException;
import com.carsite.exception.NameException;
import com.carsite.model.AthToken;
import com.carsite.model.LoginData;
import com.carsite.repository.AccountRepository;
import com.carsite.security.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    TokenGenerator tokenGenerator;
    @PostMapping("signup")
    public ResponseEntity<AthToken> signUp(@RequestBody Account account) {
        AthToken athToken = new AthToken();
        if (account.getEmail() == null || !account.getEmail().contains("@")) {
            throw new EmailException("Email must contain an @ sign.");
        }
        if(accountRepository.existsByEmail(account.getEmail())) {
            throw new EmailException("An account with that email already exists.");
        }
        if(account.getFirstName() != null && accountRepository.existsByFirstName(account.getFirstName())) {
            throw new NameException( "An account with this name already exists.");
        }
        String hashedPassword = bCryptPasswordEncoder.encode(account.getPassword());
        account.setPassword(hashedPassword);
        accountRepository.save(account);
        athToken.setToken(tokenGenerator.generateToken(account.getEmail(),false));
        athToken.setAccountId(account.getId());

        return ResponseEntity.ok().body(athToken);
    }
    @PostMapping("login")
    public ResponseEntity<AthToken> login(@RequestBody LoginData loginData) {

        Account account = accountRepository.findAccountByEmail(loginData.getEmail());
        AthToken authToken = new AthToken();

        if (bCryptPasswordEncoder.matches(loginData.getPassword(), account.getPassword())) {
            authToken.setToken(tokenGenerator.generateToken(account.getEmail(), account.isAdmin()));
            authToken.setAccountId(account.getId());
        }
        return ResponseEntity.ok().body(authToken);
    }


}
