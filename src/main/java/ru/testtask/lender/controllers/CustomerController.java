package ru.testtask.lender.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ru.testtask.lender.dto.CustomerSearchDTO;
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
        model.addAttribute("customerSearchDTO", new CustomerSearchDTO());
        return "customers_list";
    }

    @GetMapping("/search")
    public String seachCustomers(
        @RequestParam(name = "name", required = false) String name,
        @RequestParam(name = "surname", required = false) String surname,
        @RequestParam(name = "patronymic", required = false) String patronymic,
        @RequestParam(name = "passportNumber", required = false) Long passportNumber,
        @RequestParam(name="phoneNumber", required = false) String phoneNumber,
        Model model){
        List<Customer> customers = customersRepository.getBy(
            name,
            surname,
            patronymic,
            passportNumber,
            phoneNumber);
            
        model.addAttribute("customers", customers);
        model.addAttribute("customerSearchDTO", new CustomerSearchDTO());

        return "customers_list";
    }
}
