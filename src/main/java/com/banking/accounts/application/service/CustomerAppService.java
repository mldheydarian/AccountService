package com.banking.accounts.application.service;

import com.banking.accounts.domain.account.dto.AccountOutputDto;
import com.banking.accounts.domain.account.entity.Account;
import com.banking.accounts.domain.account.service.IAccountService;
import com.banking.accounts.domain.customer.dto.CustomerAccountsDto;
import com.banking.accounts.domain.customer.entity.Customer;
import com.banking.accounts.domain.customer.service.ICustomerService;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CustomerAppService implements ICustomerAppService {
    private final IAccountService accountService;
    private final ICustomerService customerService;
    final Logger logger;

    public CustomerAppService(IAccountService accountService, ICustomerService customerService, Logger logger) {
        this.accountService = accountService;
        this.customerService = customerService;
        this.logger = logger;
    }
    @Override
    @Transactional
    public CustomerAccountsDto getCustomerAccounts(@Valid @RequestParam Integer customerId)  {

        //Finds Customer and related Accounts
        Customer customer = customerService.getCustomerById(customerId).get();
        List<Account> accountList = accountService.getAccounts(customerId);

        // Maps AccountList to output format
        List<AccountOutputDto>  accountOutputList =  accountList.stream().map( acc ->
             new AccountOutputDto(acc.getBalance(),acc.getStatus(),acc.getAccountType().getEnumAccountType()))
                 .collect(Collectors.toList());

        //Combines  Customer and related Accounts to output
        return new CustomerAccountsDto(){{
            setCustomerAccounts(accountOutputList);
            setCustomerInformation(customer);
        }};

    }
}
