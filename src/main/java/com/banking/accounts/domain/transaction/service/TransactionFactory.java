package com.banking.accounts.domain.transaction.service;

import com.banking.accounts.domain.account.entity.CurrentAccount;
import com.banking.accounts.domain.transaction.dto.TransactionDto;
import com.banking.accounts.domain.transaction.entity.DepositTransaction;
import com.banking.accounts.domain.transaction.entity.Transaction;
import com.banking.accounts.domain.transaction.entity.WithdrawTransaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionFactory implements ITransactionFactory{

    public Transaction createTransaction(TransactionDto transactionDto) {
        switch (transactionDto.getEnumTransactionType()) {
            case Deposit:
                return new DepositTransaction(transactionDto);
            case WithDraw:
                return new WithdrawTransaction(transactionDto);
        }
        return null; // TODO: throw exception
    }
}
