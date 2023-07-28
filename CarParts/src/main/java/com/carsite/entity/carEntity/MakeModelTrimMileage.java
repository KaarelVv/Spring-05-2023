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
public class MakeModelTrimMileage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public int make_model_trim_id;
    public String fuel_tank_capacity;
    public int combined_mpg;
    public int epa_city_mpg;
    public int epa_highway_mpg;
    public int range_city;
    public int range_highway;
    public int battery_capacity_electric;
    public String epa_time_to_charge_hr_240v_electric;
    public int epa_kwh_100_mi_electric;
    public int range_electric;
    public int epa_highway_mpg_electric;
    public int epa_city_mpg_electric;
    public int epa_combined_mpg_electric;
}
