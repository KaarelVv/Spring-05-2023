package com.kaarel.webshop.repository;

import com.kaarel.webshop.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Long> {

    Person findPersonByEmail(String email);
}
