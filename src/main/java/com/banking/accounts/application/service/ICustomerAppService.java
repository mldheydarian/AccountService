package com.banking.accounts.application.service;

import com.banking.accounts.domain.customer.dto.CustomerAccountsDto;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

public interface ICustomerAppService {
    CustomerAccountsDto getCustomerAccounts(@Valid @RequestParam Integer customerId);
}
