package com.carsite.repository;

import com.carsite.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AccountRepository extends JpaRepository <Account, Long> {

    boolean existsByEmail(String email);

    Account findAccountByEmail(String email);


}
