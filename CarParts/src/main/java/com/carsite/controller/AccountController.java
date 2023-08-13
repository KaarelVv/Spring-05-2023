package com.carsite.controller;


import com.carsite.entity.Account;
import com.carsite.entity.Ad;
import com.carsite.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class AccountController {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("account")
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @GetMapping("account/{id}/ads")
    public ResponseEntity<Account> getAccountWithAds(@PathVariable Long id) {
        Optional<Account> optionalAccount = accountRepository.findById(id);

        if (!optionalAccount.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Account account = optionalAccount.get();
        List<Ad> ads = account.getAds();
        // This is  to avoid infinite loop during serialization
        account.setAds(new ArrayList<>());
        account.setAds(ads);

        return ResponseEntity.ok(account);
    }
    @GetMapping("user-account")
    public Account getPersonAccount() {
        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        return accountRepository.findAccountByEmail(email);
    }
    @PutMapping("account")
    public List<Account> editAccount(@RequestBody Account account) {
        if (accountRepository.existsById(account.getId())) {
            String hashedPassword = bCryptPasswordEncoder.encode(account.getPassword());
            account.setPassword(hashedPassword);
            accountRepository.save(account);
        }
        return accountRepository.findAll();
    }
}


//    @PostMapping("account")
//    public List<Account> addAccount(@RequestBody Account account){
//        accountRepository.save(account);
//        return accountRepository.findAll();
//    }


