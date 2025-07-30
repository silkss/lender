package ru.testtask.lender.services;

import org.springframework.stereotype.Service;
import ru.testtask.lender.models.Customer;
import ru.testtask.lender.models.LoanContract;
import ru.testtask.lender.models.LoanDecision;
import ru.testtask.lender.models.LoanRequest;
import ru.testtask.lender.types.LoanRequestStatus;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class LoanDecisionMaker {
    private LoanDecision approved(LoanRequest loanRequest) {
        loanRequest.setLoanRequestStatus(LoanRequestStatus.APPROVED);
        int numberOfDays = (int)(Math.random() * 365);
        BigDecimal loanAmount = new BigDecimal(loanRequest.getDesiredLoanAmount());
        return new LoanDecision(LoanRequestStatus.APPROVED, numberOfDays, loanAmount);
    }

    private LoanDecision declined(LoanRequest loanRequest) {
        loanRequest.setLoanRequestStatus(LoanRequestStatus.DECLINED);
        return new LoanDecision(LoanRequestStatus.DECLINED, 0, null);
    }

    public LoanDecision makeDecision(LoanRequest loanRequest) {
        return (Math.random() > 0.5 ?
                approved(loanRequest) :
                declined(loanRequest));
    }
}
