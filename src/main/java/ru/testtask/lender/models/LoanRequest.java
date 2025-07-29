package ru.testtask.lender.models;

import lombok.Getter;
import lombok.Setter;
import ru.testtask.lender.types.LoanRequestStatus;

@Getter
@Setter
public class LoanRequest {
    private int desiredLoanAmount;
    private LoanRequestStatus loanRequestStatus;

    public LoanRequest() {}
}
