package com.carsite.entity.carEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MakeModelTrimBody {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public int make_model_trim_id;
    public String type;
    public int doors;
    public String length;
    public String width;
    public int seats;
    public String height;
    public String wheel_base;
    public int front_track;
    public int rear_track;
    public String ground_clearance;
    public String cargo_capacity;
    public String max_cargo_capacity;
    public int curb_weight;
    public int gross_weight;
    public int max_payload;
    public String max_towing_capacity;
}
