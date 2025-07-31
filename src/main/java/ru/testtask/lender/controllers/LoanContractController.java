package ru.testtask.lender.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.testtask.lender.models.LoanContract;
import ru.testtask.lender.repositories.LoanContractRepository;
import ru.testtask.lender.types.LoanContractStatus;

@Controller
@RequestMapping("/loan/contract")
public class LoanContractController {
    
    private LoanContractRepository loanContractRepository;

    public LoanContractController(LoanContractRepository loanContractRepository) {
        this.loanContractRepository = loanContractRepository;
    }

    @GetMapping("/all") 
    public String getAll(Model model) {
        List<LoanContract> contracts = loanContractRepository.getAll();
        model.addAttribute("contracts", contracts);
        return "loan_contracts_list";
    }
    
    @GetMapping("/signed")
    public String getSigned(Model model) {
        List<LoanContract> contracts = loanContractRepository.getSigned();
        model.addAttribute("contracts", contracts);
        return "loan_contracts_list";
    }

    @GetMapping("/{id}")
    public String getLoanRequestById(@PathVariable("id") long id, Model model) {
        LoanContract contract  = loanContractRepository.getById(id);
        model.addAttribute("contract", contract);
        return "loan_contract_info";
    }

    @GetMapping("/{id}/sign")
    public String signContract(@PathVariable("id") long id, Model model) {
        LoanContract contract = loanContractRepository.getById(id);
        contract.setLoanContractStatus(LoanContractStatus.SIGNED);
        contract.setSignDate(new Date());
        // contract.signContract();
        loanContractRepository.update(contract);
        return "redirect:/locan/contract/" + contract.getId();
    }
}
