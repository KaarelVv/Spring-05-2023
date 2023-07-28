package com.carsite.repository.carRepo;

import com.carsite.entity.carEntity.Make;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MakeRepository extends JpaRepository <Make, Long> {
    Make findByName(String name);
}
