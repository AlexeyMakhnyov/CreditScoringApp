package com.makhnyov.creditscoringapp.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "odds")
public class Odds {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "age")
    private double age;

    @Column(name = "gender")
    private double gender;

    @Column(name = "marital")
    private double marital;

    @Column(name = "dependents")
    private double dependents;

    @Column(name = "income")
    private double income;

    @Column(name = "experience")
    private double experience;

    @Column(name = "real_estate")
    private double ownHousing;

    @Column(name = "monthly_payment")
    private double monthlyPayment;

    @Column(name = "intercept")
    private double intercept;
}
