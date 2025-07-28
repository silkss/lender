package ru.testtask.lender.controllers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.testtask.lender.dto.LoanRequestDto;
import ru.testtask.lender.models.Customer;

@Controller
@RequestMapping("/loan/request")
public class LoanRequestController {

    @Autowired
    private SessionFactory sessionFactory;

    @GetMapping("/create")
    public String getLoanRequest(Model model) {
        model.addAttribute("loanRequestDto", new LoanRequestDto());
        return "loan_request_create";
    }

    @PostMapping("/create")
    public String receiveLoanRequest(@ModelAttribute LoanRequestDto loanRequestDto) {
        Customer customer = new Customer(
                loanRequestDto.getName(),
                loanRequestDto.getSurname(),
                loanRequestDto.getPatronymic(),
                loanRequestDto.getPassportNumber(),
                loanRequestDto.getAddress(),
                loanRequestDto.getPhoneNumber(),
                loanRequestDto.getEmploymentStartDate(),
                loanRequestDto.getPost(),
                loanRequestDto.getOrganizationName());

        Session session = sessionFactory.openSession();

        session.beginTransaction();
        session.persist(customer);
        session.getTransaction().commit();

        session.close();
        return "loan_request_created";
    }
}
