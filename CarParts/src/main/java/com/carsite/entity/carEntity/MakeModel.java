package com.carsite.entity.carEntity;


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
public class MakeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public int make_id;
    public String name;
}
