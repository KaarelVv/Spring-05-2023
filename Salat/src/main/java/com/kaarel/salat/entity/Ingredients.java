package com.kaarel.salat.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ingredients {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    private String ingredientName;
    private Double fat;
    private Double carb;
    private Double protein;
    public void validateMacro() throws IllegalArgumentException {
        double total = this.protein + this.carb + this.fat;
        if (total > 100) {
            throw new IllegalArgumentException("Total macros exceed 100%");
        }
    }




}
