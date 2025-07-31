package ru.testtask.lender.controllers;

import org.springframework.ui.Model;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import ru.testtask.lender.dto.LoanRequestDto;
import ru.testtask.lender.models.Customer;
import ru.testtask.lender.models.LoanContract;
import ru.testtask.lender.models.LoanRequest;
import ru.testtask.lender.repositories.CustomersRepository;
import ru.testtask.lender.repositories.LoanRequestRepository;
import ru.testtask.lender.services.LoanDecisionMaker;


@Controller
@RequestMapping("/loan/request")
public class LoanRequestController {

    private final CustomersRepository customersRepository;
    private final LoanDecisionMaker loanDecisionMaker;
    private final LoanRequestRepository loanRequestRepository;

    public LoanRequestController(
            CustomersRepository customersRepository,
            LoanRequestRepository loanRequestRepository,
            LoanDecisionMaker loanDecisionMaker) {
        this.customersRepository = customersRepository;
        this.loanRequestRepository = loanRequestRepository;
        this.loanDecisionMaker = loanDecisionMaker;
    }

    @GetMapping("/approved")
    public String getApprovedRequests(Model model) {
        List<LoanRequest> requests = loanRequestRepository.getApprovedRequests();
        model.addAttribute("requests", requests);
        return "loan_requests_list";
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
