package com.banking.accounts.domain.transaction.service;

import com.banking.accounts.domain.account.dto.AccountDto;
import com.banking.accounts.domain.account.entity.Account;
import com.banking.accounts.domain.transaction.dto.TransactionDto;
import com.banking.accounts.domain.transaction.entity.Transaction;

public interface ITransactionService {
    Transaction createTransaction(TransactionDto transactionDto) ;

}
