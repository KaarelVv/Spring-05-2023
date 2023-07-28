package com.carsite.repository.carRepo;

import com.carsite.entity.carEntity.MakeModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MakeModelRepository extends JpaRepository <MakeModel, Long> {
}
