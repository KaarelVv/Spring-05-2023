package com.carsite.repository;

import com.carsite.entity.Images;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository <Images, Long> {
}
