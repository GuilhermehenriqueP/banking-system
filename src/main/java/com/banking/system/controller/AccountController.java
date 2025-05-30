package com.banking.system.controller;


import java.util.UUID;
import java.util.List;
import com.banking.system.domain.Account;
import com.banking.system.dto.request.CreateAccountRequest;
import com.banking.system.dto.request.DepositRequest;
import com.banking.system.dto.response.AccountResponse;
import com.banking.system.service.AccountService;
import com.banking.system.service.interfaces.BankingOperations;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController<account> {

    private final AccountService accountService;
    private final BankingOperations bankingOperations;


    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<AccountResponse> createAccount(@RequestBody @Valid CreateAccountRequest request) {
        Account account = new Account();
        account.setBalance(request.getInitialBalance());
        Account savedAccount = accountService.createAccount(account);
        return ResponseEntity.ok(AccountResponse.from(savedAccount));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> getAccount(@PathVariable UUID id) {
        Account account = accountService.findById(id);
        return ResponseEntity.ok(AccountResponse.from(account));
    }

    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService.findAll();
    }

    @PostMapping("/{id}/deposit")
    public ResponseEntity<AccountResponse> deposit(
            @PathVariable UUID id,
            @RequestBody @Valid DepositRequest request) {

        Account account = bankingOperations.deposit(id, request.getAmount());
        return ResponseEntity.ok(AccountResponse.from(account));
    }


}
