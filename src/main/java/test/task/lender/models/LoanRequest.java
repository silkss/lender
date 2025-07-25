package test.task.lender.models;

import lombok.Getter;
import lombok.Setter;
import test.task.lender.types.LoanRequestStatus;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class LoanRequest {
    private int id;
    private BigDecimal desiredLoanAmount;
    private Date createdTime;

    private LoanRequestStatus status;
    private int approvedPeriod;
    private BigDecimal approvedLoanAmount;

    public LoanRequest(
            int id,
            BigDecimal desiredLoanAmount,
            Date createdTime,
            LoanRequestStatus status,
            int approvedPeriod,
            BigDecimal approvedLoanAmount) {
        this.id = id;
        this.desiredLoanAmount = desiredLoanAmount;
        this.createdTime = createdTime;
        this.status = status;
        this.approvedPeriod = approvedPeriod;
        this.approvedLoanAmount = approvedLoanAmount;
    }
}
