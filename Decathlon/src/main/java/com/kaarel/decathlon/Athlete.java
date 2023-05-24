package com.kaarel.decathlon;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity //teeb tabeliks
@Getter
@Setter
public class Athlete {
    @Id
    private Long id;
    private String name;
    private String country;
    private Integer age;

    private Integer result;
    public Athlete(String fields, Integer result) {
        this.result = result;

    }
    public Athlete() {
    }
    public void setResult(Fields field, double result) {
    }
}
