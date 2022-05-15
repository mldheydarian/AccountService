package com.banking.accounts.domain.account.service;

import com.banking.accounts.domain.account.dto.AccountDto;
import com.banking.accounts.domain.account.entity.Account;
import com.banking.accounts.domain.account.entity.CurrentAccount;
import com.banking.accounts.domain.account.entity.EnumAccountType;
import com.banking.accounts.domain.account.repository.IAccountRepository;
import com.banking.accounts.domain.transaction.dto.TransactionDto;
import com.banking.accounts.domain.transaction.entity.DepositTransaction;
import com.banking.accounts.domain.transaction.entity.EnumTransactionStatus;
import com.banking.accounts.domain.transaction.entity.EnumTransactionType;
import com.banking.accounts.domain.transaction.entity.Transaction;
import com.banking.accounts.domain.transaction.service.ITransactionFactory;
import com.banking.accounts.domain.transaction.service.ITransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

@RunWith(SpringRunner.class)
@SpringBootTest()
class AccountServiceTest {

    @MockBean
    IAccountFactory accountFactory;
    @MockBean
    ITransactionFactory transactionFactory;
    @MockBean
    ITransactionService transactionService;
    @MockBean
    IAccountRepository accountRepository;

    @Autowired
    IAccountService accountService;
    AccountDto accountDto;
    Account account;
    TransactionDto transactionDto;
    Transaction initialTransaction;

    @BeforeEach
    public void setUp() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1991, 9, 21 );
        accountDto = new AccountDto(1, EnumAccountType.CurrentAccount, BigDecimal.valueOf(10),"Arman.heydarian@gmail.com", calendar.getTime());
        account= new CurrentAccount(accountDto);
        account.setId(1);
        transactionDto = new TransactionDto(EnumTransactionStatus.Posted,"Account Initializing Deposit", EnumTransactionType.Deposit,account.getId(),account.getInitialCredit());
        initialTransaction =new DepositTransaction(transactionDto);
    }

    @Test
    void createAccount() {
        // Arrange
        Mockito.when(accountFactory.createAccount(Mockito.any(AccountDto.class))).thenReturn(account);
        Mockito.when(accountRepository.save(Mockito.any(Account.class))).thenReturn(account);
        Mockito.when(transactionService.createTransaction(Mockito.any(TransactionDto.class))).thenReturn(initialTransaction);
        //Action
        account = accountService.createAccount(accountDto);

        //Assert
        assertEquals(1,account.getId() );
        assertInstanceOf(String.class,account.getAccountNumber().number);
        assertEquals( accountDto.getInitialCredit() ,account.getBalance());

    }
}