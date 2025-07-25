package test.task.lender.dto;

import lombok.Getter;
import lombok.Setter;
import test.task.lender.models.EmploymentInformation;
import test.task.lender.models.LoanDecision;
import test.task.lender.models.LoanRequest;
import test.task.lender.models.User;
import test.task.lender.types.LoanRequestStatus;
import test.task.lender.types.MaritalStatus;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class LoanRequestDTO {
    private long passportNumber;
    private String name;
    private String surname;
    private String patronymic;
    private MaritalStatus maritalStatus;
    private String address;
    private String phoneNumber;
    private EmploymentInformation employmentInformation;

    private BigDecimal desiredLoanAmount;

    public LoanRequestDTO(
            long passportNumber,
            String name, String surname, String patronymic,
            MaritalStatus maritalStatus,
            String address,
            String phoneNumber,
            EmploymentInformation employmentInformation,
            BigDecimal desiredLoanAmount) {
        this.passportNumber = passportNumber;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.maritalStatus = maritalStatus;
        this.employmentInformation = employmentInformation;
        this.desiredLoanAmount = desiredLoanAmount;
    }

    public LoanRequestDTO() { }

    public User getUser() {
        return new User(
                0,
                this.passportNumber,
                this.name, this.surname, this.patronymic,
                this.maritalStatus,
                this.address,
                this.phoneNumber,
                this.employmentInformation);
    }

    public LoanRequest getLoanRequest() {
        return new LoanRequest(
                0,
                this.desiredLoanAmount,
                new Date(),
                LoanRequestStatus.ACCEPTED,
                0,
                new BigDecimal(0));
    }
}
