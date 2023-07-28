package com.carsite.model;


import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

@Data
public class TrimVehicleData {
    public MakeModel make_model;
    public MakeModelTrimBody make_model_trim_body;
    public MakeModelTrimEngine make_model_trim_engine;
    public MakeModelTrimMileage make_model_trim_mileage;
    public ArrayList<MakeModelTrimExteriorColor> make_model_trim_exterior_colors;
    public ArrayList<MakeModelTrimInteriorColor> make_model_trim_interior_colors;
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

@Data
class MakeModelTrimBody{
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
@Data
class MakeModelTrimEngine{
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
@Data
class MakeModelTrimExteriorColor{
    public int id;
    public int make_model_trim_id;
    public String name;
    public String rgb;
}
@Data
class MakeModelTrimInteriorColor{
    public int id;
    public int make_model_trim_id;
    public String name;
    public String rgb;

}
@Data
class MakeModelTrimMileage{
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
