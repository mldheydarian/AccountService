package com.banking.accounts.application.service;

import com.banking.accounts.domain.account.dto.AccountDto;
import com.banking.accounts.domain.account.entity.Account;
import com.banking.accounts.domain.account.repository.IAccountRepository;
import com.banking.accounts.domain.account.service.IAccountFactory;
import com.banking.accounts.domain.account.service.IAccountService;
import com.banking.accounts.domain.transaction.dto.TransactionDto;
import com.banking.accounts.domain.transaction.entity.EnumTransactionStatus;
import com.banking.accounts.domain.transaction.entity.EnumTransactionType;
import com.banking.accounts.domain.transaction.entity.Transaction;
import com.banking.accounts.domain.transaction.service.ITransactionService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class AccountAppService implements IAccountAppService {

    // The constructor is handled by Lombok
    final IAccountService accountService;
    final ITransactionService transactionService;

    @Override
    @Transactional
    public Account createAccount(AccountDto accountDto)
    {
        Account newAccount = accountService.createAccount(accountDto);
        if (accountDto.getInitialCredit().compareTo(BigDecimal.ZERO) > 0) // if amount is larger than 0
        {
            TransactionDto transactionDto = new TransactionDto(EnumTransactionType.Deposit,EnumTransactionStatus.Posted,accountDto.getInitialCredit(),newAccount.getId(), "Account Initializing Deposit");
            Transaction initialTransaction = transactionService.createTransaction(transactionDto);
            newAccount = accountService.updateAccountBalance(newAccount,initialTransaction.getFinalAmount());
            transactionService.updateTransactionStatus(initialTransaction,EnumTransactionStatus.Confirmed);
        }
        return newAccount;
    }

}
