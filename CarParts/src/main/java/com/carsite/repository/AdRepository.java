package com.carsite.repository;

import com.carsite.entity.Account;
import com.carsite.entity.Ad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AdRepository extends JpaRepository <Ad, Long> {

    List<Ad> findAllByOrderByCreationDateDesc();
    List<Ad> findAllByAccount(Account account);


}
