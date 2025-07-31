package ru.testtask.lender.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.testtask.lender.types.LoanContractStatus;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@Entity
@Table(name = "loan_contracts")
public class LoanContract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int loanPeriodInDays;
    private BigDecimal loanAmount;
    private LoanContractStatus loanContractStatus;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date signDate;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    private Customer customer;

    @OneToOne(mappedBy = "loanContract")
    private LoanRequest loanRequest;

    public LoanContract() { }
    public LoanContract(
        int loanPeriodInDays,
        BigDecimal loanAmount,
        LoanContractStatus loanContractStatus,
        Customer customer,
        LoanRequest loanRequest
    ) {
        this.loanPeriodInDays = loanPeriodInDays;
        this.loanAmount = loanAmount;
        this.loanContractStatus = loanContractStatus;
        this.customer = customer;
        this.loanRequest = loanRequest;
    }

    public void signContract() {
        this.loanContractStatus = LoanContractStatus.SIGNED;
        this.signDate = new Date();
    }
}
