package ru.testtask.lender.models;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import ru.testtask.lender.types.MaritalStatus;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(mappedBy = "customer", cascade = CascadeType.PERSIST)
    private Set<LoanRequest> loanRequests;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.PERSIST)
    private Set<LoanContract> loanContracts;

    public Customer() { }
    public LoanRequest addLoanRequest(LoanRequest loanRequest) {
        if (loanRequests == null) {
            loanRequests = new HashSet<LoanRequest>();
        }
        loanRequests.add(loanRequest.setCustomer(this));
        return loanRequest;
    }

    public void addLoanContract(LoanContract loanContract){
        if (loanContracts == null) {
            loanContracts = new HashSet<LoanContract>();
        }
        loanContract.setCustomer(this);
        loanContracts.add(loanContract);
    }
}
