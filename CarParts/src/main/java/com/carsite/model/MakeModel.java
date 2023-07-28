package com.carsite.model;

import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class MakeModel {
    @ManyToOne
    public Make make;
    public int id;
    public int make_id;
    public String name;
}
