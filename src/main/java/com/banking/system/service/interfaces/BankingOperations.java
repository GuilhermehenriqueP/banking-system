package com.banking.system.service.interfaces;

import com.banking.system.domain.Account;
import java.util.UUID;
import java.math.BigDecimal;

public interface BankingOperations {

    Account deposit(UUID accountId, BigDecimal amount);
    Account withdraw(UUID accountId, BigDecimal amount);
    Account transfer(UUID fromAccountId, BigDecimal amount, UUID toAccountId);
    BigDecimal getBalance(UUID accountId);
}
