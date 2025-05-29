package com.banking.system.controller;

import com.banking.system.domain.Account;
import com.banking.system.service.AccountService;
import org.hibernate.validator.constraints.UUID;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController<account> {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    @GetMapping("/{id}")
    public Account getAccount(@PathVariable UUID id) {
        return accountService.findById(id);
    }

    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService.findAll();
    }

}
