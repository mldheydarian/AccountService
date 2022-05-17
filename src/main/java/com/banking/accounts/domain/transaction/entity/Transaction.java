package com.banking.accounts.domain.transaction.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public abstract class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer id;

    @NotNull
    @DecimalMin(value = "0.0")
    protected BigDecimal transactionAmount;

    @NotNull
    @DecimalMin(value = "0.0")
    protected BigDecimal finalAmount;

    @NotNull
    protected EnumTransactionStatus status;

    @NotNull
    protected Integer accountId;

    protected Date postingDate;
    protected String description;

    public Transaction(EnumTransactionStatus status, String description , Integer accountId ) {
        this.status = status;
        this.description = description;
        this.accountId= accountId;
        this.postingDate = new Date();
    }

    public void updateStatus (EnumTransactionStatus status)
    {
        this.status = status;
    }



}
