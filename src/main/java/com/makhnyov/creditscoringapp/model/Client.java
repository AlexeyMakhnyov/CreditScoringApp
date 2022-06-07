package com.makhnyov.creditscoringapp.model;

import com.makhnyov.creditscoringapp.service.crypto.CryptoConvertor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
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
    @NotBlank(message = "Введите фамилию, имя и отчество")
    private String fullName;

    @Column(name = "email")
    @Convert(converter = CryptoConvertor.class)
    @NotBlank(message = "Введите адрес электронной почты")
    @Email(message = "Адрес электронной почты введён не верно")
    private String email;

    @Column(name = "phone")
    @Convert(converter = CryptoConvertor.class)
    @NotNull(message = "Введите номер телефона")
    @Size(min = 11, max = 11, message = "Номер телефона введён неверно")
    private String phone;

    @Column(name = "passport_series")
    @Convert(converter = CryptoConvertor.class)
    @Size(min = 4, max = 4, message = "Серия паспорта не введена")
    private String passportSeries;

    @Column(name = "passport_id")
    @Convert(converter = CryptoConvertor.class)
    @Size(min = 6, max = 6, message = "Номер паспорта не введён")
    private String passportId;

    @Column(name = "dob")
    @NotNull(message = "Введите дату рождения")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;

    @OneToOne
    @JoinColumn(name = "gender")
    private Gender gender;

    @Column(name = "registration")
    @Convert(converter = CryptoConvertor.class)
    @NotBlank(message = "Введите место регистрации")
    private String registration;

    @OneToOne
    @JoinColumn(name = "education")
    private Education education;

    @Column(name = "work_place")
    @Convert(converter = CryptoConvertor.class)
    @NotBlank(message = "Введите место работы")
    private String workPlace;

    @OneToOne
    @JoinColumn(name = "position")
    private Position position;

    @Column(name = "experience")
    @Max(value = 45, message = "Опыт работы введён не верно")
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

    public int getExperience() {
        return experience;
    }

    //получение возраста клиента
    public double getAge() {
        return Period.between(dob, LocalDate.now()).getYears();
    }
}
