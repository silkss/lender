package ru.testtask.lender.models;

import lombok.Getter;
import lombok.Setter;
import ru.testtask.lender.types.LoanContractStatus;

import java.math.BigDecimal;

@Getter
@Setter
public class LoanContract {
    private long id;
    private int loanPeriodInDays;
    private BigDecimal loanAmount;
    private LoanContractStatus loanContractStatus;
}
