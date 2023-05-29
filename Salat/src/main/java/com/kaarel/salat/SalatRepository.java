package com.kaarel.salat;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SalatRepository extends JpaRepository<Food,String> {
}
