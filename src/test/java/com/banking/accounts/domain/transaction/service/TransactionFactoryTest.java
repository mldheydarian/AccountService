package com.banking.accounts.domain.transaction.service;

import com.banking.accounts.domain.account.dto.AccountDto;
import com.banking.accounts.domain.account.entity.CurrentAccount;
import com.banking.accounts.domain.account.entity.EnumAccountType;
import com.banking.accounts.domain.account.entity.SavingAccount;
import com.banking.accounts.domain.account.service.AccountFactory;
import com.banking.accounts.domain.account.service.IAccountFactory;
import com.banking.accounts.domain.transaction.dto.TransactionDto;
import com.banking.accounts.domain.transaction.entity.DepositTransaction;
import com.banking.accounts.domain.transaction.entity.EnumTransactionStatus;
import com.banking.accounts.domain.transaction.entity.EnumTransactionType;
import com.banking.accounts.domain.transaction.entity.WithdrawTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;


class TransactionFactoryTest {

    ITransactionFactory transactionFactory;
    TransactionDto depositTransactionDto;
    TransactionDto withdrawTransactionDto;


    @BeforeEach
    void setUp() {
        transactionFactory =new TransactionFactory();
        depositTransactionDto = new TransactionDto(EnumTransactionStatus.Posted,"Deposit Money", EnumTransactionType.Deposit,1,BigDecimal.valueOf(10));
        withdrawTransactionDto = new TransactionDto(EnumTransactionStatus.Posted,"WithDraw Money", EnumTransactionType.WithDraw,1,BigDecimal.valueOf(20));
    }

    @Test
    void createDepositTransaction() {
        assertInstanceOf(DepositTransaction.class , transactionFactory.createTransaction(depositTransactionDto));
    }
    @Test
    void createWithdrawTransaction() {
        assertInstanceOf(WithdrawTransaction.class , transactionFactory.createTransaction(withdrawTransactionDto));
    }

}
