package com.banking.accounts.domain.account.dto;

import com.banking.accounts.domain.account.entity.EnumAccountType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
    @NotNull
    @Min(value=0)
    private Integer customerId;

    @NotNull
    private EnumAccountType enumAccountType;

    @NotNull
    @DecimalMin(value = "0.0")
    private BigDecimal initialCredit;

}
