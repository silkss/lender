package ru.testtask.lender.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerSearchDTO {
    private String name;
    private String surname;
    private String patronymic;

    private Long passportNumber;
    private String phoneNumber;

    public CustomerSearchDTO() {}
}
