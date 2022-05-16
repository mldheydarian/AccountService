package com.banking.accounts.domain.transaction.service;

import com.banking.accounts.domain.transaction.dto.TransactionDto;
import com.banking.accounts.domain.transaction.entity.Transaction;

public interface ITransactionFactory  {
    Transaction createTransaction(TransactionDto transactionDto);
}
