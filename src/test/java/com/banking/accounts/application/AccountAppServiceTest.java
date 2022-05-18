package com.banking.accounts.application;

import com.banking.accounts.application.service.IAccountAppService;
import com.banking.accounts.domain.account.dto.AccountDto;
import com.banking.accounts.domain.account.entity.Account;
import com.banking.accounts.domain.account.entity.AccountType;
import com.banking.accounts.domain.account.entity.CurrentAccount;
import com.banking.accounts.domain.account.entity.EnumAccountType;
import com.banking.accounts.domain.account.service.IAccountService;
import com.banking.accounts.domain.transaction.dto.TransactionDto;
import com.banking.accounts.domain.transaction.entity.DepositTransaction;
import com.banking.accounts.domain.transaction.entity.EnumTransactionStatus;
import com.banking.accounts.domain.transaction.entity.EnumTransactionType;
import com.banking.accounts.domain.transaction.entity.Transaction;
import com.banking.accounts.domain.transaction.service.ITransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;



@RunWith(SpringRunner.class)
@SpringBootTest()
class AccountAppServiceTest {

    @MockBean
    IAccountService accountService;
    @MockBean
    ITransactionService transactionService;

    @Autowired
    IAccountAppService accountAppService;

    AccountDto currentAccountDto;
    Account currentAccount;
    TransactionDto transactionDto;
    Transaction initialTransaction;

    @BeforeEach
    public void setUp() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1991, 9, 21 );
        ArrayList<AccountType> accountTypeList = new ArrayList<AccountType>() {{
            add(new AccountType((long) 1, EnumAccountType.CurrentAccount, BigDecimal.valueOf(100)));
            add(new AccountType((long) 2, EnumAccountType.SavingAccount, BigDecimal.valueOf(10)));
        }};
        currentAccountDto = new AccountDto(1, EnumAccountType.CurrentAccount, BigDecimal.valueOf(10),"Arman.heydarian@gmail.com", calendar.getTime());
        currentAccount = new CurrentAccount(currentAccountDto,accountTypeList.get(0));
        currentAccount.setId(1);
        currentAccount.setBalance(BigDecimal.valueOf(10));
        transactionDto = new TransactionDto(EnumTransactionType.Deposit,EnumTransactionStatus.Posted,currentAccount.getInitialCredit(),currentAccount.getId(),"Account Initializing Deposit" );
        initialTransaction =new DepositTransaction(transactionDto);
    }

    @Test
    void createAccount() {
        // Arrange
        Mockito.when(accountService.createAccount(Mockito.any(AccountDto.class))).thenReturn(currentAccount);
        Mockito.when(accountService.updateAccountBalance(Mockito.any(Account.class),(Mockito.any(BigDecimal.class)))).thenReturn(currentAccount);
        Mockito.when(transactionService.createTransaction(Mockito.any(TransactionDto.class))).thenReturn(initialTransaction);
        Mockito.when(transactionService.updateTransactionStatus(Mockito.any(Transaction.class),Mockito.any(EnumTransactionStatus.class))).thenReturn(initialTransaction);

        //Action
        currentAccount = accountAppService.createAccount(currentAccountDto);
        //Assert
        assertEquals(1,currentAccount.getId());
        assertEquals(currentAccountDto.getInitialCredit(),currentAccount.getBalance());

    }
}