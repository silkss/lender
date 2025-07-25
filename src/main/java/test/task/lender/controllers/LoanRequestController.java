package test.task.lender.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

import test.task.lender.dto.LoanRequestDTO;
import test.task.lender.models.LoanRequest;
import test.task.lender.models.User;
import test.task.lender.types.LoanRequestStatus;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/loanrequest")
public class LoanRequestController {
    @GetMapping("/create")
    public String getRequestForm(Model model) {
        model.addAttribute("loanRequestDTO", new LoanRequestDTO());
        return "loan_request";
    }

    @PostMapping("/create")
    public String receiveLoanRequest(@ModelAttribute LoanRequestDTO loanRequestDTO, Model model) {


        User user = loanRequestDTO.getUser();
        // TODO: проверить есть ли пользователь в базе данные.
        // TODO: создать пользователя если его нет в БД.

        LoanRequest loanRequest = loanRequestDTO.getLoanRequest();
        // TODO: одобрить или неодобрить кредит.
        // TODO: записать заявку в БД

        int loanRequestId = new Random().nextInt();

        model.addAttribute("user", user);
        return "redirect:/loanrequest/" + loanRequestId;
    }

    @GetMapping("/all")
    public String getAllLoans(Model model) {
        // TODO: выгрузка всех заявок из БД
        List<LoanRequest> loanRequests = new ArrayList<LoanRequest>(4);
        loanRequests.add(new LoanRequest(1, new BigDecimal(1_000_000), new Date(), LoanRequestStatus.DECLINED, 0, new BigDecimal(0)));
        loanRequests.add(new LoanRequest(2, new BigDecimal(10_000_000), new Date(), LoanRequestStatus.DECLINED, 0, new BigDecimal(0)));
        loanRequests.add(new LoanRequest(3, new BigDecimal(2_000_000), new Date(), LoanRequestStatus.DECLINED, 0, new BigDecimal(0)));
        loanRequests.add(new LoanRequest(4, new BigDecimal(3_000_000), new Date(), LoanRequestStatus.DECLINED, 0, new BigDecimal(0)));

        model.addAttribute("loanRequests", loanRequests);
        return "loan_requests_list";
    }

    @GetMapping("/{id}")
    public String getLoanById(@PathVariable(name = "id") int id, Model model) {
        // TODO: найти в БД заявку.
        // TODO: подтянуть пользователя, создавшего эту заявку.
        LoanRequest loan = new LoanRequest(
                0,
                new BigDecimal(1_000_000),
                new Date(),
                LoanRequestStatus.ACCEPTED,
                0,
                new BigDecimal(0));

        model.addAttribute("loan", loan);
        return "loan_info";
    }
}
