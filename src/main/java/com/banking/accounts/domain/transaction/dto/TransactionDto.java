package com.banking.accounts.domain.transaction.dto;

import com.banking.accounts.domain.transaction.entity.EnumTransactionStatus;
import com.banking.accounts.domain.transaction.entity.EnumTransactionType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {
    @NotNull
    private EnumTransactionType enumTransactionType;

    @NotNull
    private EnumTransactionStatus status;


    @NotNull
    @DecimalMin(value = "0.0")
    private BigDecimal amount;
    @NotNull
    private Integer accountId;

    private String description;




}
