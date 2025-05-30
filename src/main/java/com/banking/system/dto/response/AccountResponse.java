package com.banking.system.dto.response;

import com.banking.system.domain.Account;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter  // ← OBRIGATÓRIO para JSON
@Setter
public class AccountResponse {

    private UUID id;
    private String accountNumber;
    private Integer accountDigit;
    private BigDecimal balance;
    private String status;
    private LocalDateTime createdAt;

    // Construtor que converte de Account
    public static AccountResponse from(Account account) {
        AccountResponse response = new AccountResponse();
        response.id = account.getId();
        response.accountNumber = account.getAccountNumber();
        response.accountDigit = account.getAccountDigit();
        response.balance = account.getBalance();
        response.status = account.getStatus().name();
        response.createdAt = account.getCreatedAt();
        return response;
    }
}
