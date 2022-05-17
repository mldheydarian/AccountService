package com.banking.accounts.domain.account.service;

import com.banking.accounts.domain.account.dto.AccountDto;
import com.banking.accounts.domain.account.entity.Account;
import com.banking.accounts.domain.transaction.entity.Transaction;

import java.math.BigDecimal;
import java.util.List;


public interface IAccountService {
    Account createAccount(AccountDto accountDto) ;
    List<Account> getAccounts(Integer customerId) ;
    Account updateAccountBalance(Account account, BigDecimal newBalance);
}
