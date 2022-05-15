package com.banking.accounts.domain.transaction.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
    protected BigDecimal transactionAmount;
    protected BigDecimal finalAmount;
    protected EnumTransactionStatus status;
    protected Date postingDate;
    protected String description;
    protected Integer accountId;


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
