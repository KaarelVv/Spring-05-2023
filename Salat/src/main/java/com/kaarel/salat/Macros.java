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
public class Macros {
    @Id
    private String nameOfIngridient;
    private double fat;
    private double carb;
    private double protein;
    @OneToMany
    private Ingredients ingredients;

    public Macros(String name, double fat, double carb, double protein) {
        this.nameOfIngridient = name;
        this.fat = fat;
        this.carb = carb;
        this.protein = protein;

    }

//    public Macros validateMacro() {
//        if (fat + carb + protein < 100) {
//            throw new IllegalArgumentException("Invalid macro!");
//        }
//        return this;
//    }
public static double[] validateMacro(double protein, double carbs, double fats) throws IllegalArgumentException {
    double total = protein + carbs + fats;
    if (total > 100) {
        throw new IllegalArgumentException("Total macros exceed 100%");
    } else {
        double proteinPercent = (protein / total) * 100;
        double carbsPercent = (carbs / total) * 100;
        double fatsPercent = (fats / total) * 100;
        return new double[]{proteinPercent, carbsPercent, fatsPercent};
    }
}
}
