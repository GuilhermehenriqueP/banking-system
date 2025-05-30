package com.banking.system.service;

import com.banking.system.domain.Account;
import com.banking.system.domain.Transaction;
import com.banking.system.domain.enums.TransactionType;
import com.banking.system.repository.AccountRepository;
import com.banking.system.repository.TransactionRepository;
import com.banking.system.service.interfaces.BankingOperations;
import jakarta.transaction.Transactional;
import com.banking.system.domain.enums.AccountStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class BankingOperationsImpl implements BankingOperations {

    private AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public BankingOperationsImpl(AccountRepository accountRepository,
                                 TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }


    @Override
    @Transactional
    public Account withdraw(UUID accountId, BigDecimal amount) {
        Account account = findActiveAccount(accountId);

        if(account.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient balance");

        }
        BigDecimal newBalance = account.getBalance().subtract(amount);
        account.setBalance(newBalance);

        return accountRepository.save(account);
    }

    @Override
    @Transactional
    public Account transfer(UUID fromAccountId, BigDecimal amount, UUID toAccountId) {
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    public BigDecimal getBalance(UUID accountId) {
        Account account = findActiveAccount(accountId);
        return account.getBalance();
    }

    @Override
    @Transactional
    public Account deposit(UUID accountId, BigDecimal amount) {
        Account account = findActiveAccount(accountId);

        BigDecimal newBalance = account.getBalance().add(amount);
        account.setBalance(newBalance);

        Account savedAccount = accountRepository.save(account);

        // Registrar transação
        createTransaction(account, amount, TransactionType.DEPOSIT, newBalance, "Deposit");

        return savedAccount;
    }

    private Account findActiveAccount(UUID accountId) {

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if(account.getStatus() != AccountStatus.ACTIVE){
            throw new RuntimeException("Account not active");
        }
        return account;
    }

    private void createTransaction(Account account, BigDecimal amount,
                                   TransactionType type, BigDecimal balanceAfter, String description) {
        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setAmount(amount);
        transaction.setType(type);
        transaction.setBalanceAfter(balanceAfter);
        transaction.setDescription(description);

        transactionRepository.save(transaction);
    }
}
