package com.makhnyov.creditscoringapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "credit")
public class Credit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "amount")
    @NotNull(message = "Введите сумму займа")
    @DecimalMin(value = "10000", message = "Слишком маленькая сумма займа (минимальная сумма 10000)")
    @DecimalMax(value = "1000000", message = "Слишком большая сумма займа (максимальная сумма 1000000)")
    @Digits(integer=7, fraction=2)
    private BigDecimal amount;

    @Column(name = "monthly_payment")
    private BigDecimal monthlyPayment;

    @Column(name = "approved")
    private boolean approved;

    @OneToOne
    @JoinColumn(name = "term")
    private Term term;

    @OneToOne
    @JoinColumn(name = "rate")
    private Rate rate;

}
