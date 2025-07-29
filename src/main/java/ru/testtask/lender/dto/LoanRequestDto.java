package ru.testtask.lender.dto;

import ru.testtask.lender.models.Customer;
import ru.testtask.lender.models.LoanRequest;

public class LoanRequestDto {

    private Customer customer;
    public void setCustomer(Customer customer) { this.customer = customer; }
    public Customer getCustomer() { return this.customer; }

    private LoanRequest loanRequest;
    public void setLoanRequest(LoanRequest loanRequest) { this.loanRequest = loanRequest; }
    public LoanRequest getLoanRequest() { return this.loanRequest; }

    public LoanRequestDto() {
        this.customer = new Customer();
        this.loanRequest = new LoanRequest();
    }
}
