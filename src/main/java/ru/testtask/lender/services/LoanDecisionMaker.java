package ru.testtask.lender.services;

import org.springframework.stereotype.Service;
import ru.testtask.lender.models.Customer;
import ru.testtask.lender.models.LoanContract;
import ru.testtask.lender.models.LoanRequest;
import ru.testtask.lender.types.LoanRequestStatus;

import java.util.Optional;

@Service
public class LoanDecisionMaker {
    private Optional<LoanContract> approved(Customer customer, LoanRequest loanRequest) {
        loanRequest.setLoanRequestStatus(LoanRequestStatus.APPROVED);
        LoanContract contract = new LoanContract() {

        };
        return Optional.empty();
    }
    private Optional<LoanContract> declined(Customer customer, LoanRequest loanRequest) {
        loanRequest.setLoanRequestStatus(LoanRequestStatus.DECLINED);
        return Optional.empty();
    }
    public Optional<LoanContract> makeDecision(Customer customer, LoanRequest loanRequest) {
        return (Math.random() > 0.5 ?
                approved(customer, loanRequest) :
                declined(customer, loanRequest));
    }
}
