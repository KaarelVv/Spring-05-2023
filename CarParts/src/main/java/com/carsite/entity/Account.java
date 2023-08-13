package com.carsite.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;


import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private Long phoneNumber;
    private Date dateCreated;
    private String password;
    @ColumnDefault("false")
    private boolean admin;
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)//cascade.all when using delete will remove all ads related to that account!
    //@JoinColumn// means that account always needs to have ad. Not the best scenario...
    @JsonManagedReference//  for handling bidirectional relationships, owning side of the relationship
    private List<Ad> ads = new ArrayList<>();

}
