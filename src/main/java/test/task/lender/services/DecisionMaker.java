package test.task.lender.services;

import test.task.lender.models.LoanRequest;
import test.task.lender.types.LoanRequestStatus;

public class DecisionMaker {
    private LoanRequest approve(LoanRequest loanRequest) {
        loanRequest.setStatus(LoanRequestStatus.APPROVED);
        // TODO: установка случайного периода от 1 до 12 месяцев.
        // TODO: установка случайной суммы от 10% желаемой суммы до 100%.
        return loanRequest;
    }

    private LoanRequest decline(LoanRequest loanRequest) {
        loanRequest.setStatus(LoanRequestStatus.DECLINED);
        return loanRequest;
    }

    public LoanRequest makeDecision(LoanRequest loanRequest) {
        if (loanRequest.getStatus() != LoanRequestStatus.ACCEPTED) {
            throw new RuntimeException("Решение уже принято!");
        }
        double decision = Math.random();
        return  (decision >= .5 ?  approve(loanRequest) : decline(loanRequest));
    }
}
