package ru.testtask.lender.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class LoanRequestDto {

    private String name;
    private String surname;
    private String patronymic;

    private long passportNumber;

    private String address;
    private String phoneNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date employmentStartDate;
    private String post;
    private String organizationName;

    public LoanRequestDto() {}

    public LoanRequestDto(
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
