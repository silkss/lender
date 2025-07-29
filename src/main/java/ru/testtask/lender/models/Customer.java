package ru.testtask.lender.models;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import ru.testtask.lender.types.MaritalStatus;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String surname;
    private String patronymic;
    private MaritalStatus maritalStatus;

    @Column(unique = true)
    private long passportNumber;

    private String address;
    private String phoneNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date employmentStartDate;
    private String post;
    private String organizationName;

    public Customer() { }

    public Customer(
            String name,
            String surname,
            String patronymic,
            MaritalStatus maritalStatus,
            long passportNumber,
            String address,
            String phoneNumber,
            Date employmentStartDate,
            String post,
            String organizationName) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.maritalStatus = maritalStatus;
        this.passportNumber = passportNumber;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.employmentStartDate = employmentStartDate;
        this.post = post;
        this.organizationName = organizationName;
    }
}
