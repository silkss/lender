package ru.testtask.lender.models;

import jakarta.persistence.*;
import lombok.Getter;
import ru.testtask.lender.types.LoanContractStatus;

import java.math.BigDecimal;

@Getter
@Entity
@Table(name = "loan_contracts")
public class LoanContract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int loanPeriodInDays;
    private BigDecimal loanAmount;
    private LoanContractStatus loanContractStatus;
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    private Customer customer;

    @OneToOne(mappedBy = "loanContract")
    private LoanRequest loanRequest;

    public LoanContract setLoanPeriodInDays(int loanPeriodInDays){
        this.loanPeriodInDays = loanPeriodInDays;
        return this;
    }

    public LoanContract setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
        return this;
    }

    public LoanContract setLoanContractStatus(LoanContractStatus loanContractStatus) {
        this.loanContractStatus = loanContractStatus;
        return this;
    }

    public LoanContract setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public LoanContract setLoanRequest(LoanRequest loanRequest) {
        this.loanRequest = loanRequest;
        return this;
    }
}
