package com.walller.api.wallerjava.controllers;

import java.util.List;
import com.walller.api.wallerjava.Models.*;
import com.walller.api.wallerjava.repositories.AccountRepository;
import com.walller.api.wallerjava.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/account")
public class Account {

    @Autowired
    private AccountRepository accountRepo;

    @Autowired
    private AccountService accountService;

    @GetMapping()
    public List<AccountEntity> GetAllAccounts() {
        return accountRepo.findAll();
    }

    @GetMapping("/balance")
    public AccountEntity GetAccountBalance(@RequestParam(name = "id") Integer id) throws Exception {
        return accountService.getAccountBalance(id);
    }

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public AccountEntity AddUser(@RequestBody AccountEntity body) {
        return accountService.addUserAccount(body);
    }
}
