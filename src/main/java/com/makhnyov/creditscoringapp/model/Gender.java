package com.makhnyov.creditscoringapp.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "gender")
public class Gender implements Serializable {

    @Id
    private long id;

    @Column(name = "gender")
    private String gender;

    @Column(name = "gender_le")
    private int genderLe;
}
