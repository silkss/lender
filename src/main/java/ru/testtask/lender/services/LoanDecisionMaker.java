package ru.testtask.lender.services;

import org.springframework.stereotype.Service;
import ru.testtask.lender.models.LoanDecision;
import ru.testtask.lender.models.LoanRequest;
import ru.testtask.lender.types.LoanRequestStatus;

import java.math.BigDecimal;

@Service
public class LoanDecisionMaker {
    private LoanDecision approved(LoanRequest loanRequest) {
        loanRequest.setLoanRequestStatus(LoanRequestStatus.APPROVED);
        double r = Math.random();
        int numberOfDays = (int)(r * 365);
        BigDecimal loanAmount = new BigDecimal(loanRequest.getDesiredLoanAmount() * r);
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
