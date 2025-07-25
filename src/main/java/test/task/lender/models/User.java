package test.task.lender.models;

import lombok.Getter;
import lombok.Setter;
import test.task.lender.types.MaritalStatus;

@Getter
@Setter
public class User {
    private int id;
    private long passportNumber;
    private String name;
    private String surname;
    private String patronymic;
    private MaritalStatus maritalStatus;
    private String address;
    private String phoneNumber;
    private EmploymentInformation employmentInformation;

    public User(
            int id,
            long passportNumber,
            String name,
            String surname,
            String patronymic,
            MaritalStatus maritalStatus,
            String address,
            String phoneNumber,
            EmploymentInformation employmentInformation) {
        this.id = id;
        this.passportNumber = passportNumber;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.maritalStatus = maritalStatus;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.employmentInformation = employmentInformation;
    }
}
