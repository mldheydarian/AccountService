package com.banking.accounts.domain.customer.dto;

import com.banking.accounts.domain.account.dto.AccountOutputDto;
import com.banking.accounts.domain.customer.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAccountsDto {
    private Customer customerInformation;
    private List<AccountOutputDto> customerAccounts;
}
