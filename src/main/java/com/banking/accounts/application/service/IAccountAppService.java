package com.banking.accounts.application.service;

import com.banking.accounts.domain.account.dto.AccountDto;
import com.banking.accounts.domain.account.entity.Account;

import java.util.List;


public interface IAccountAppService {
    Account createAccount(AccountDto accountDto) ;

}
