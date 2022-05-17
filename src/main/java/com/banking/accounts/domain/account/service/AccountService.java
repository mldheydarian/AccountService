package com.banking.accounts.domain.account.service;

import com.banking.accounts.domain.account.dto.AccountDto;
import com.banking.accounts.domain.account.entity.Account;
import com.banking.accounts.domain.account.repository.IAccountRepository;
import com.banking.accounts.domain.transaction.service.ITransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

import org.apache.logging.log4j.Logger;

@Service
@AllArgsConstructor
public class AccountService implements IAccountService{

    // The constructor is handled by Lombok
    final IAccountFactory accountFactory;
    final IAccountRepository accountRepository;
    final ITransactionService transactionService;
    final Logger logger;

    @Override
    @Transactional
    public Account createAccount(AccountDto accountDto)
    {
        Account newAccount = accountFactory.createAccount(accountDto);
        accountRepository.save(newAccount);
        logger.info("A new Account added successfully, Account Number : "+ newAccount.getAccountNumber().toString());
        return newAccount;
    }

    @Override
    public List<Account> getAccounts(Integer customerId) {

        return accountRepository.findByCustomerId(customerId);
    }

    @Override
    public Account updateAccountBalance(Account account, BigDecimal newBalance) {
        account.updateBalance(newBalance);
        accountRepository.save(account);
        logger.info("Account Balance with Number:" + account.getAccountNumber().toString() + "has been updated");
        return  account;
    }
}
