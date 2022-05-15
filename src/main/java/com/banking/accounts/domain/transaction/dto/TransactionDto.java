package com.banking.accounts.domain.transaction.dto;

import com.banking.accounts.domain.transaction.entity.EnumTransactionStatus;
import com.banking.accounts.domain.transaction.entity.EnumTransactionType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {

    private EnumTransactionStatus status;
    private String description;
    private EnumTransactionType enumTransactionType;
    private Integer accountId;
    private BigDecimal amount;

}
