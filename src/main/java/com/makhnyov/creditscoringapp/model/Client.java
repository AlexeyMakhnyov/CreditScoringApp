package com.makhnyov.creditscoringapp.model;

import com.makhnyov.creditscoringapp.service.crypto.CryptoConvertor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "full_name")
    @Convert(converter = CryptoConvertor.class)
    @NotNull(message = "Введите фамилию, имя и отчество")
    private String fullName;

    @Column(name = "email")
    @Convert(converter = CryptoConvertor.class)
    @NotNull(message = "Введите адрес электронной почты")
    @Email(message = "Адрес электронной почты введён не верно")
    private String email;

    @Column(name = "phone")
    @Convert(converter = CryptoConvertor.class)
    @NotNull(message = "Введите номер телефона")
    @Size(min = 11, max = 11, message = "Номер телефона введён неверно")
    private String phone;

    @Column(name = "passport_series")
    @Convert(converter = CryptoConvertor.class)
    @NotNull(message = "Введите серию паспорта")
    @Size(min = 4, max = 4, message = "Серия паспорта введена не правильно")
    private String passportSeries;

    @Column(name = "passport_id")
    @Convert(converter = CryptoConvertor.class)
    @NotNull(message = "Введите номер паспорта")
    @Size(min = 6, max = 6, message = "Номер паспорта введен не правильно")
    private String passportId;

    @Column(name = "dob")
    private LocalDate dob;

    @OneToOne
    @JoinColumn(name = "gender")
    private Gender gender;

    @Column(name = "registration")
    @Convert(converter = CryptoConvertor.class)
    @NotNull(message = "Введите место регистрации")
    private String registration;

    @OneToOne
    @JoinColumn(name = "education")
    private Education education;

    @Column(name = "work_place")
    @Convert(converter = CryptoConvertor.class)
    @NotNull(message = "Введите место работы")
    private String workPlace;

    @OneToOne
    @JoinColumn(name = "position")
    private Position position;

    @Column(name = "experience")
    @NotNull(message = "Введите опыт работы")
    private int experience;

    @Column(name = "income")
    @NotNull(message = "Введите ваш доход")
    private BigDecimal income;

    @Column(name = "marital")
    private boolean marital;

    @Column(name = "own_housing")
    private boolean ownHousing;

    @Column(name = "dependents")
    private int dependents;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    private List<Credit> credits;

    //получение возраста клиента
    public double getAge() {
        return Period.between(dob, LocalDate.now()).getYears();
    }
}
