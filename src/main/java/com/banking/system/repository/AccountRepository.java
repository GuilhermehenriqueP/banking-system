package com.banking.system.repository;

import com.banking.system.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {


    <T> ScopedValue<T> findById(org.hibernate.validator.constraints.UUID id);

    Optional<Account> findTopByOrderByAccountNumberDesc();
}
