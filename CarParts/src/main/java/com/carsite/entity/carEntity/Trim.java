package com.carsite.entity.carEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Trim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public int make_model_id;
    public int year;
    public String name;
    public String description;
    public int msrp;
    public int invoice;
    public Date created;
    public Date modified;
}
