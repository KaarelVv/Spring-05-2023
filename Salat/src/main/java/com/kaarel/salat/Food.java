package com.kaarel.salat;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter

@NoArgsConstructor
@Entity
@Table(name = "food")
public class Food {
    @Id
    private String name;

    private double fat;
    private double carb;
    private double protein;
    @ManyToOne
    private FoodIngridients foodIngridients;

    public Food(String name, double fat, double carb, double protein) {
        this.name = name;
        this.fat = fat;
        this.carb = carb;
        this.protein = protein;

    }

    public Food validateMacro() {
        if (fat + carb + protein < 100) {
            throw new IllegalArgumentException("Invalid macro!");
        }
        return this;
    }
}
