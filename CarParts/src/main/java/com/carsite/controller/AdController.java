package com.carsite.controller;

import com.carsite.entity.Account;
import com.carsite.entity.Ad;
import com.carsite.entity.Images;
import com.carsite.repository.AccountRepository;
import com.carsite.repository.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@RestController
public class AdController {

    @Autowired
    AdRepository adRepository;
    @Autowired
    ImageController imageController;
    @Autowired
    AccountRepository accountRepository;

    @GetMapping("ad")
    public List<Ad> getAllAd() {
        return adRepository.findAllByOrderByCreationDateDesc();
    }
    @GetMapping("ad/{id}")
    public Ad getAdById(@PathVariable Long id) {
        return adRepository.findById(id).orElseThrow();
    }
    @PostMapping("ad")
    public List<Ad> addAd(@RequestParam("title") String title,
                          @RequestParam("description") String description,
                          @RequestParam("type") String type,
                          @RequestParam("price") Double price,
                          @RequestParam("active") boolean active,
                          @RequestParam("files") MultipartFile[] multipartFile,
                          @RequestParam("accountId") Long accountId) throws Exception {

        Ad ad = new Ad();
        ad.setTitle(title);
        ad.setDescription(description);
        ad.setType(type);
        ad.setPrice(price);
        ad.setActive(active);
        ad.setCreationDate(new Date());

        // Retrieve the Account using the provided ID
        Account account = accountRepository.findById(accountId)
                .get();

        ad.setAccount(account);
        account.getAds().add(ad);//adding uploaded ad to that account

        imageAttach(multipartFile, ad);
        accountRepository.save(account);
//        adRepository.save(ad);

        return adRepository.findAllById(Collections.singleton(accountId));
    }
    @PutMapping("ad/{id}")
    public ResponseEntity<Ad> updateAd(@PathVariable Long id,
                                       @RequestParam("title") String title,
                                       @RequestParam("description") String description,
                                       @RequestParam("type") String type,
                                       @RequestParam("price") Double price,
                                       @RequestParam("active") boolean active,
                                       @RequestParam("files") MultipartFile[] multipartFile,
                                       @RequestParam("accountId") Long accountId) {

        try {
            Ad ad = adRepository.findById(id).orElseThrow(() -> new Exception("Ad not found with id : " + id));
            ad.setTitle(title);
            ad.setDescription(description);
            ad.setType(type);
            ad.setPrice(price);
            ad.setActive(active);

            Account account = accountRepository.findById(accountId)
                    .orElseThrow(() -> new Exception("Account not found with id : " + accountId));
            ad.setAccount(account);

            imageAttach(multipartFile, ad);

            adRepository.save(ad);

            return new ResponseEntity<>(ad, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
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

    private void imageAttach(MultipartFile[] multipartFile, Ad ad) throws Exception {
        for(MultipartFile file : multipartFile) {
            ResponseEntity<?> uploadResponse = imageController.uploadFile(file);
            if (uploadResponse.getStatusCode() == HttpStatus.OK) {
                Images adImages = new Images();
                adImages.setImageName((String) uploadResponse.getBody());
                adImages.setAd(ad);
                ad.getAdImages().add(adImages);
            }
        }
    }




}
