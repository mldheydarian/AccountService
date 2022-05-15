package com.banking.accounts.domain.transaction.entity;

import com.banking.accounts.domain.transaction.dto.TransactionDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class WithdrawTransaction extends Transaction {

    private Double commission = 0.02;

    public WithdrawTransaction(TransactionDto transactionDto) {
        super(transactionDto.getStatus(), transactionDto.getDescription(),transactionDto.getAccountId());
        this.transactionAmount = transactionDto.getAmount();
        this.finalAmount = transactionDto.getAmount().multiply( BigDecimal.valueOf(commission)).negate();

    }





}
