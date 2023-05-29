package com.kaarel.salat;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FoodIngridients {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    private long weight;
    @OneToMany
    private List<Food> food;


}
