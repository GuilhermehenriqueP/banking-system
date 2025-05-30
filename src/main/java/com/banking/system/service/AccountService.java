package com.banking.system.service;

import com.banking.system.domain.Account;
import com.banking.system.domain.enums.AccountStatus;
import com.banking.system.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository){

        this.accountRepository = accountRepository;
    }


    public Account createAccount(Account account){
        account.setAccountNumber(generateNextAccountNumber());
        account.setAccountDigit(generateAccountDigit());
        account.setStatus(AccountStatus.ACTIVE);
        account.setCreatedAt(LocalDateTime.now());
        return accountRepository.save(account);
    }
    public Account findById(UUID id){

        return (Account) accountRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Conta não encontrada")
        );
    }

    public List<Account> findAll(){
        return accountRepository.findAll();
    }

    private String generateNextAccountNumber() {
        Optional<Account> lastAccount = accountRepository.findTopByOrderByAccountNumberDesc();

        if (lastAccount.isPresent()) {
            int currentNumber = Integer.parseInt(String.valueOf(lastAccount.get().getAccountNumber()));
            int nextNumber = currentNumber + 1;
            return String.format("%05d", nextNumber); // 00001, 00002, etc.
        } else {
            return "00001"; // Primeira conta
        }
    }

    private int generateAccountDigit() {
        Random random = new Random();
        return random.nextInt(10);
    }


}
