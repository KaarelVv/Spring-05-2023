package com.carsite.model;

import lombok.Data;

import java.util.Date;

@Data
 public class Trim {
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
