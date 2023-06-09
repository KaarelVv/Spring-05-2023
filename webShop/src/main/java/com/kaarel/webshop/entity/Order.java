package com.kaarel.webshop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders") //
@SequenceGenerator(name = "seq",initialValue = 1234451364,allocationSize = 1)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq")
    private Long id;
    private String paid;
    private double totalSum ;
    private Date creationDate;
    @OneToMany
    private List<OrderRow> orderRows;
    @ManyToOne
    private Person person;

}
