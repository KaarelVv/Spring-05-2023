package com.carsite.repository.carRepo;

import com.carsite.entity.carEntity.Trim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrimRepository extends JpaRepository<Trim, Long> {
}
