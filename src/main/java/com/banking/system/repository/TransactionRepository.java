package com.banking.system.repository;

import com.banking.system.domain.Transaction;
import com.banking.system.domain.enums.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    List<Transaction> findByAccountIdOrderByCreatedAtDesc(UUID accountId);

    List<Transaction> findByAccountIdAndTypeOrderByCreatedAtDesc(UUID accountId, TransactionType type);
}
