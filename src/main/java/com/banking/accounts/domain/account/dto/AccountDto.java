package com.banking.accounts.domain.account.dto;

import com.banking.accounts.domain.account.entity.EnumAccountType;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
    private Integer customerId;
    private EnumAccountType enumAccountType;
    private BigDecimal initialCredit;
    private String email;
    private Date birthDate;
}
