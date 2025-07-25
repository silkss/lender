package test.task.lender.models;

import lombok.Getter;
import lombok.Setter;
import test.task.lender.types.LoanRequestStatus;

import java.math.BigDecimal;

@Getter
@Setter
public class LoanDecision {
    private LoanRequestStatus status;
    private int period;
    private BigDecimal loanAmount;

    public LoanDecision(LoanRequestStatus status, int period, BigDecimal loanAmount) {
        this.status = status;
        this.period = period;
        this.loanAmount = loanAmount;
    }

    public LoanDecision() {
        this.status = LoanRequestStatus.ACCEPTED;
    }
}
