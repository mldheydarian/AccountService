package com.banking.accounts.domain.transaction.entity;

import com.banking.accounts.domain.transaction.dto.TransactionDto;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.math.BigDecimal;


@Setter
@NoArgsConstructor
@Entity
public class DepositTransaction extends Transaction {

    public DepositTransaction(TransactionDto transactionDto) {
        super(transactionDto.getStatus(), transactionDto.getDescription(),transactionDto.getAccountId());
        this.transactionAmount = transactionDto.getAmount();
        this.finalAmount = transactionDto.getAmount();
    }

}
