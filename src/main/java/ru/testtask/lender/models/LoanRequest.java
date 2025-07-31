package ru.testtask.lender.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.testtask.lender.services.LoanDecisionMaker;
import ru.testtask.lender.types.LoanContractStatus;
import ru.testtask.lender.types.LoanRequestStatus;

@Getter
@Setter
@Entity
@Table(name = "loan_requests")
public class LoanRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int desiredLoanAmount;
    private LoanRequestStatus loanRequestStatus;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "contract_id", referencedColumnName = "id")
    private LoanContract loanContract;

    public LoanRequest() {
        this.desiredLoanAmount = 100000;
    }

    public void setId(long id) { this.id = id; }
    public void setDesiredLoanAmount(int desiredLoanAmount) { this.desiredLoanAmount = desiredLoanAmount; }
    public void setLoanRequestStatus(LoanRequestStatus loanRequestStatus) { this.loanRequestStatus = loanRequestStatus; }
    public LoanRequest setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public LoanContract makeLoanContractIfApproved(LoanDecisionMaker decisionMaker) {
        LoanDecision decision = decisionMaker.makeDecision(this);
        loanRequestStatus = decision.getLoanRequestStatus();
        if (loanRequestStatus == LoanRequestStatus.DECLINED) {
            return null;
        }
        else {
            this.loanContract = new LoanContract(
                decision.getLoanPeriodInDays(),
                decision.getLoanAmount(), 
                LoanContractStatus.UNSIGNED,
                customer,
                this);
            return this.loanContract;
        }
    }
}
