package com.makhnyov.creditscoringapp.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = "terms")
@Getter
@Setter
public class Term{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "term")
    private String term;

    @Column(name = "months_term")
    private int monthsTerm;

}
