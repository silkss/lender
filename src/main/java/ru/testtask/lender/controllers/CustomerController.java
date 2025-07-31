package ru.testtask.lender.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ru.testtask.lender.models.Customer;
import ru.testtask.lender.repositories.CustomersRepository;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    private final CustomersRepository customersRepository;

    public CustomerController(CustomersRepository customersRepository) {
        this.customersRepository = customersRepository;
    }
    
    @GetMapping("/all")
    public String getAllCustomers(Model model) {
        List<Customer> customers = customersRepository.getAll();
        model.addAttribute("customers", customers);
        return "customers_list";
    }
}
