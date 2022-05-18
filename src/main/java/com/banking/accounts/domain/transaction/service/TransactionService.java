package com.banking.accounts.domain.transaction.service;

import com.banking.accounts.application.exception.RecordNotFoundException;
import com.banking.accounts.domain.transaction.dto.TransactionDto;
import com.banking.accounts.domain.transaction.entity.EnumTransactionStatus;
import com.banking.accounts.domain.transaction.entity.Transaction;
import com.banking.accounts.domain.transaction.repository.ITransactionRepository;
import lombok.AllArgsConstructor;
import net.bytebuddy.implementation.bytecode.Throw;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class TransactionService implements ITransactionService {


    final ITransactionFactory transactionFactory;
    final ITransactionRepository transactionRepository;
    final Logger logger;

    public Transaction createTransaction(TransactionDto transactionDto)
    {
        Transaction newTransaction = transactionFactory.createTransaction(transactionDto);
        newTransaction = transactionRepository.save(newTransaction);
        logger.info("A New Transaction Saved successfully, Transaction ID : "+ newTransaction.getId());
        return newTransaction;
    }

    public Transaction updateTransactionStatus(Transaction transaction,EnumTransactionStatus enumTransactionStatus)
    {
        Optional<Transaction> persistedTransaction = transactionRepository.findById(transaction.getId());
        persistedTransaction.ifPresent(
                transaction1 -> transaction1.updateStatus(enumTransactionStatus)
        );
        if (persistedTransaction.isPresent())
        {
            persistedTransaction.get().updateStatus(enumTransactionStatus);
            transactionRepository.save(persistedTransaction.get());
            logger.info("Transaction Status Updated to :"+enumTransactionStatus+" successfully, Transaction ID : "+ transaction.getId());
            return persistedTransaction.get();
        }
        else
            throw  new RecordNotFoundException("The Transaction not found in DB");
    }
}
