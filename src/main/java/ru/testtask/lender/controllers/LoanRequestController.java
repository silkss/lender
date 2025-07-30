package ru.testtask.lender.controllers;

import org.springframework.ui.Model;
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
    private final LoanRequestRepository loanRequestRepository;
    private final LoanDecisionMaker loanDecisionMaker;

    public LoanRequestController(
            CustomersRepository customersRepository,
            LoanRequestRepository loanRequestRepository,
            LoanDecisionMaker loanDecisionMaker) {
        this.customersRepository = customersRepository;
        this.loanRequestRepository = loanRequestRepository;
        this.loanDecisionMaker = loanDecisionMaker;
    }

    @GetMapping("/create")
    public String getLoanRequest(Model model) {
        model.addAttribute("loanRequestDto", new LoanRequestDto());
        return "loan_request_create";
    }

    @PostMapping("/create")
    public String receiveLoanRequest(@ModelAttribute LoanRequestDto loanRequestDto) {
        // TODO: проверку, если пользователь существует предлложить обновить или обновить автоматически.
        Customer customer = loanRequestDto.getCustomer();
        LoanRequest loanRequest = customer.addLoanRequest(loanRequestDto.getLoanRequest());
        LoanContract contract = loanRequest.makeLoanContractIfApproved(loanDecisionMaker);

        if (contract != null) {
            customer.addLoanContract(contract);
        }

        customersRepository.add(customer);

        return "redirect:/loan/request/" + loanRequest.getId();
    }

    @GetMapping("/{id}")
    public String getLoanRequestById(@PathVariable("id") long id, Model model) {
        LoanRequest loanRequest = loanRequestRepository.getById(id);
        model.addAttribute("loanRequest", loanRequest);
        return "loan_request_info";
    }
}
