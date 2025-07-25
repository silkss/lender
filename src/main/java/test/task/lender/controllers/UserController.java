package test.task.lender.controllers;

import org.springframework.ui.Model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import test.task.lender.models.LoanContract;
import test.task.lender.models.LoanRequest;
import test.task.lender.models.User;
import test.task.lender.types.LoanRequestStatus;

import java.util.ArrayList;

@Controller
@RequestMapping("/user")
public class UserController {
    @GetMapping("/all")
    public String getAllUsers(Model model) {
        return "users_list";
    }

    @GetMapping("/{id}")
    public String getUserInfo(@PathVariable(name = "id") int id, Model model) {
        // TODO: вытащить пользователя из БД вместе с его запросами на кредит и кредитнными контрактами.
        return "user_info";
    }
}
