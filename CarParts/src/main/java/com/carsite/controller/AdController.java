package com.carsite.controller;

import com.carsite.entity.Account;
import com.carsite.entity.Ad;
import com.carsite.entity.Images;
import com.carsite.exception.InvalidPriceFormatException;
import com.carsite.repository.AccountRepository;
import com.carsite.repository.AdRepository;
import com.carsite.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@RestController
public class AdController {

    @Autowired
    AdRepository adRepository;
    @Autowired
    Service service;
    @Autowired
    AccountRepository accountRepository;

    @GetMapping("ad")
    public List<Ad> getAllAd() {
        return adRepository.findAllByOrderByCreationDateDesc();
    }

    @GetMapping("ad/{id}")
    public Ad getAdById(@PathVariable Long id) {
        return adRepository.findById(id).get();
    }

    @PostMapping("ad")
    public List<Ad> addAd(@RequestParam("title") String title,
                          @RequestParam("description") String description,
                          @RequestParam("type") String type,
                          @RequestParam("price") String priceString,
                          @RequestParam("active") boolean active,
                          @RequestParam(value = "files", required = false) MultipartFile[] multipartFile,
                          @RequestParam("accountId") Long accountId) throws Exception {


        double price;
        try {
            price = Double.parseDouble(priceString);
        } catch (NumberFormatException e) {
            throw new InvalidPriceFormatException("Price must be a number!!!!");
        }

        Ad ad = new Ad();
        ad.setTitle(title);
        ad.setDescription(description);
        ad.setType(type);
        ad.setPrice(price);
        ad.setActive(active);
        ad.setCreationDate(new Date());

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new Exception("Account not found with id : " + accountId));
        ad.setAccount(account);

        account.getAds().add(ad);

        service.imageAttach(multipartFile, ad);
        accountRepository.save(account);

//        adRepository.save(ad);
        return adRepository.findAllByAccount(account);
    }

    @PutMapping("ad/{id}")
    public Ad editAd(@PathVariable Long id,
                     @RequestParam("title") String title,
                     @RequestParam("description") String description,
                     @RequestParam("type") String type,
                     @RequestParam("price") String priceString,
                     @RequestParam("active") boolean active,
                     @RequestParam(value = "files", required = false) MultipartFile[] multipartFile
                    ) throws Exception {


        Double price;
        try {
            price = Double.parseDouble(priceString);
        } catch (NumberFormatException e) {
            throw new InvalidPriceFormatException("Price must be a number!!!!");
        }
        Ad ad = adRepository.findById(id).orElseThrow(()-> new Exception("Ad not found"));

        ad.setTitle(title);
        ad.setDescription(description);
        ad.setType(type);
        ad.setPrice(price);
        ad.setActive(active);

        service.imageAttach(multipartFile, ad);

        adRepository.save(ad);
        return ad;
    }

    @DeleteMapping("ad/{id}")
    public ResponseEntity<Void> deleteAd(@PathVariable Long id) {
        try {
            Ad ad = adRepository.findById(id).orElseThrow(() -> new Exception("Ad not found with id : " + id));
            adRepository.delete(ad);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
