package ru.testtask.lender.models;

import ru.testtask.lender.types.LoanRequestStatus;

import java.math.BigDecimal;

public class LoanDecision {
    private LoanRequestStatus loanRequestStatus;
    private int loanPeriodInDays;
    private BigDecimal loanAmount;

    public LoanRequestStatus getLoanRequestStatus() { return this.loanRequestStatus; }
    public int getLoanPeriodInDays() { return this.loanPeriodInDays; }
    public BigDecimal getLoanAmount() { return this.loanAmount; }

    public LoanDecision(LoanRequestStatus loanRequestStatus, int loanPeriodInDays, BigDecimal loanAmount) {
        this.loanRequestStatus = loanRequestStatus;
        this.loanPeriodInDays = loanPeriodInDays;
        this.loanAmount = loanAmount;
    }
}
