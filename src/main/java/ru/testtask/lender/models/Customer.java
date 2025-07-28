package ru.testtask.lender.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

import lombok.Getter;
import lombok.Setter;

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

    private long passportNumber;

    private String address;
    private String phoneNumber;

    private Date employmentStartDate;
    private String post;
    private String organizationName;

    public Customer() { }

    public Customer(
            String name,
            String surname,
            String patronymic,
            long passportNumber,
            String address,
            String phoneNumber,
            Date employmentStartDate,
            String post,
            String organizationName) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.passportNumber = passportNumber;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.employmentStartDate = employmentStartDate;
        this.post = post;
        this.organizationName = organizationName;
    }
}
