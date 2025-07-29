package ru.testtask.lender.controllers;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.testtask.lender.dto.LoanRequestDto;
import ru.testtask.lender.models.Customer;
import ru.testtask.lender.repositories.CustomersRepository;

@Controller
@RequestMapping("/loan/request")
public class LoanRequestController {

    private final CustomersRepository customersRepository;

    public LoanRequestController(CustomersRepository customersRepository) {
        this.customersRepository = customersRepository;
    }

    @GetMapping("/create")
    public String getLoanRequest(Model model) {
        model.addAttribute("loanRequestDto", new LoanRequestDto());
        return "loan_request_create";
    }

    @PostMapping("/create")
    public String receiveLoanRequest(@ModelAttribute LoanRequestDto loanRequestDto) {
        Customer customer = loanRequestDto.getCustomer();
        customersRepository.add(customer);

        return "loan_request_created";
    }
}
