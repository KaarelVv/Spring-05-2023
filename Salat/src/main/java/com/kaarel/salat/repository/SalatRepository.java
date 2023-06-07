package com.kaarel.salat.repository;

import com.kaarel.salat.entity.Ingredients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalatRepository extends JpaRepository<Ingredients,Long> {


}
