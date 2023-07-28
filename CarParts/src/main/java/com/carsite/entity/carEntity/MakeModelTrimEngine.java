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
public class MakeModelTrimEngine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public int make_model_trim_id;
    public String engine_type;
    public String fuel_type;
    public String cylinders;
    public String size;
    public int horsepower_hp;
    public int horsepower_rpm;
    public int torque_ft_lbs;
    public int torque_rpm;
    public int valves;
    public String valve_timing;
    public String cam_type;
    public String drive_type;
    public String transmission;
}
