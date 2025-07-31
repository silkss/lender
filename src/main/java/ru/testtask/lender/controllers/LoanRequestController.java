package ru.testtask.lender.controllers;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import ru.testtask.lender.dto.LoanRequestDto;
import ru.testtask.lender.models.Customer;
import ru.testtask.lender.models.LoanContract;
import ru.testtask.lender.models.LoanRequest;
import ru.testtask.lender.repositories.CustomersRepository;
import ru.testtask.lender.services.LoanDecisionMaker;


@Controller
@RequestMapping("/loan/request")
public class LoanRequestController {

    private final CustomersRepository customersRepository;
    private final LoanDecisionMaker loanDecisionMaker;

    public LoanRequestController(
            CustomersRepository customersRepository,
            LoanDecisionMaker loanDecisionMaker) {
        this.customersRepository = customersRepository;
        this.loanDecisionMaker = loanDecisionMaker;
    }

    @GetMapping("/create")
    public String getLoanRequest(Model model) {
        model.addAttribute("loanRequestDto", new LoanRequestDto());
        return "loan_request_create";
    }

    @PostMapping("/create")
    public String receiveLoanRequest(@ModelAttribute LoanRequestDto loanRequestDto, Model model) {
        Customer customer = loanRequestDto.getCustomer();
        LoanRequest loanRequest = customer.addLoanRequest(loanRequestDto.getLoanRequest());
        LoanContract contract = loanRequest.makeLoanContractIfApproved(loanDecisionMaker);

        if (contract != null) {
            customer.addLoanContract(contract);
        }

        customersRepository.add(customer);
        model.addAttribute("loanRequest", loanRequest);

        return "loan_request_info";
    }
}
