package com.banking.accounts.domain.account.dto;

import com.banking.accounts.domain.account.entity.AccountStatus;
import com.banking.accounts.domain.account.entity.EnumAccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
@AllArgsConstructor
@Getter
@Setter
public class AccountOutputDto {
    @NotNull
    @DecimalMin(value = "0.0", inclusive = true)
    private BigDecimal Balance;

    private AccountStatus status;

    private EnumAccountType accountType;
}
