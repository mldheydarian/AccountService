package com.banking.accounts.domain.transaction.service;

import com.banking.accounts.domain.transaction.dto.TransactionDto;
import com.banking.accounts.domain.transaction.entity.Transaction;
import com.banking.accounts.domain.transaction.repository.ITransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionService implements ITransactionService {


    final ITransactionFactory transactionFactory;
    final ITransactionRepository transactionRepository;

    public Transaction createTransaction(TransactionDto transactionDto)
    {
        Transaction newTransaction = transactionFactory.createTransaction(transactionDto);
        newTransaction = transactionRepository.save(newTransaction);
        return newTransaction;
    }
}
