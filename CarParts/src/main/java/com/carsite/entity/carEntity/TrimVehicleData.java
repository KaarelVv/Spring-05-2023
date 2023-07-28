package com.carsite.entity.carEntity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrimVehicleData {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "make_model_idTest", referencedColumnName = "id")
    public MakeModel make_model;
    @OneToOne(cascade = CascadeType.ALL)
    public MakeModelTrimBody make_model_trim_body;
    @OneToOne(cascade = CascadeType.ALL)
    public MakeModelTrimEngine make_model_trim_engine;
    @OneToOne(cascade = CascadeType.ALL)
    public MakeModelTrimMileage make_model_trim_mileage;
    @OneToMany(cascade = CascadeType.ALL)
    public ArrayList<MakeModelTrimExteriorColor> make_model_trim_exterior_colors;
    @OneToMany(cascade = CascadeType.ALL)
    public ArrayList<MakeModelTrimInteriorColor> make_model_trim_interior_colors;
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
