package com.kaarel.mobileshop.model;

import lombok.Data;

import java.util.ArrayList;

@Data

public class MobileShopResponce {
    public ArrayList<Product> products;
    public int total;
    public int skip;
    public int limit;
}
@Data
public
class Product{
    public int id;
    public String title;
    public String description;
    public int price;
    public double discountPercentage;
    public double rating;
    public int stock;
    public String brand;
    public String category;
    public String thumbnail;
    public ArrayList<String> images;


}
