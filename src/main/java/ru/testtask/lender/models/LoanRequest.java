package ru.testtask.lender.models;

import jakarta.persistence.*;

import ru.testtask.lender.services.LoanDecisionMaker;
import ru.testtask.lender.types.LoanContractStatus;
import ru.testtask.lender.types.LoanRequestStatus;

import javax.swing.text.html.Option;
import java.util.Optional;

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

    public LoanRequest() {}

    public void setId(long id) { this.id = id; }
    public void setDesiredLoanAmount(int desiredLoanAmount) { this.desiredLoanAmount = desiredLoanAmount; }
    public void setLoanRequestStatus(LoanRequestStatus loanRequestStatus) { this.loanRequestStatus = loanRequestStatus; }
    public LoanRequest setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public long getId() {return this.id;}
    public int getDesiredLoanAmount() {return this.desiredLoanAmount; }
    public LoanRequestStatus getLoanRequestStatus() { return this.loanRequestStatus; }
    public Customer getCustomer() {return this.customer;}

    public LoanContract makeLoanContractIfApproved(LoanDecisionMaker decisionMaker) {
        LoanDecision decision = decisionMaker.makeDecision(this);
        loanRequestStatus = decision.getLoanRequestStatus();
        if (loanRequestStatus == LoanRequestStatus.DECLINED) {
            return null;
        }
        else {
            return new LoanContract()
                    .setLoanAmount(decision.getLoanAmount())
                    .setLoanPeriodInDays(decision.getLoanPeriodInDays())
                    .setLoanContractStatus(LoanContractStatus.UNSIGNED)
                    .setCustomer(this.getCustomer())
                    .setLoanRequest(this);
        }
    }
}
