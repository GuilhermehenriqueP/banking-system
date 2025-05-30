package com.banking.system.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.UUID;
import com.banking.system.domain.enums.AccountStatus;
import org.springframework.data.annotation.CreatedDate;

@Data
@Entity
@Table(name="accounts")
public class Account {



    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id; //FK para User

    @Column(name = "account_number", length = 5)
    private String accountNumber;
    private int accountDigit;
    private BigDecimal balance;
    @Column(name = "created_at")
    @CreatedDate
    private LocalDateTime createdAt;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;

}
