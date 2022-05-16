package com.banking.accounts.domain.account.service;

import com.banking.accounts.domain.account.dto.AccountDto;
import com.banking.accounts.domain.account.entity.Account;
import com.banking.accounts.domain.account.repository.IAccountRepository;
import com.banking.accounts.domain.transaction.dto.TransactionDto;
import com.banking.accounts.domain.transaction.entity.EnumTransactionStatus;
import com.banking.accounts.domain.transaction.entity.EnumTransactionType;
import com.banking.accounts.domain.transaction.entity.Transaction;
import com.banking.accounts.domain.transaction.service.ITransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;


@Service
@AllArgsConstructor
public class AccountService implements IAccountService{

    // The constructor is handled by Lombok
    final IAccountFactory accountFactory;
    final IAccountRepository accountRepository;
    final ITransactionService transactionService;

    @Transactional
    public Account createAccount(AccountDto accountDto)
    {
        Account newAccount = accountFactory.createAccount(accountDto);
        accountRepository.save(newAccount);

        if (newAccount.getInitialCredit().compareTo(BigDecimal.ZERO) > 0) // if amount is larger than 0
        {
            TransactionDto transactionDto = new TransactionDto(EnumTransactionStatus.Posted,"Account Initializing Deposit", EnumTransactionType.Deposit,newAccount.getId(),accountDto.getInitialCredit());

            Transaction initialTransaction = transactionService.createTransaction(transactionDto);
            newAccount.updateBalance(initialTransaction.getFinalAmount());
            initialTransaction.updateStatus(EnumTransactionStatus.Confirmed);
            accountRepository.save(newAccount);
        }

        return newAccount;
    }
}
