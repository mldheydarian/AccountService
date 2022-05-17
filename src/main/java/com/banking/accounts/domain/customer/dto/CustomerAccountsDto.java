package com.banking.accounts.domain.customer.dto;

import com.banking.accounts.domain.account.dto.AccountOutputDto;
import com.banking.accounts.domain.customer.entity.Customer;
import lombok.Data;

import java.util.List;

@Data
public class CustomerAccountsDto {
    private Customer customerInformation;
    private List<AccountOutputDto> customerAccounts;
}
