package com.banking.accounts.domain.account.service;

import com.banking.accounts.domain.account.dto.AccountDto;
import com.banking.accounts.domain.account.entity.Account;
import com.banking.accounts.domain.account.entity.AccountType;
import com.banking.accounts.domain.account.entity.CurrentAccount;
import com.banking.accounts.domain.account.entity.EnumAccountType;
import com.banking.accounts.domain.account.repository.IAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

@RunWith(SpringRunner.class)
@SpringBootTest()
class AccountServiceTest {

    @MockBean
    IAccountFactory mockAccountFactory;
    @MockBean
    IAccountRepository mockAccountRepository;

    @Autowired
    IAccountService accountService;

    AccountDto currentAccountDto;
    Account currentAccount;

    @BeforeEach
    public void setUp() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1991, 9, 21 );
        ArrayList<AccountType> accountTypeList = new ArrayList<AccountType>() {{
            add(new AccountType((long) 1, EnumAccountType.CurrentAccount, BigDecimal.valueOf(100)));
            add(new AccountType((long) 2, EnumAccountType.SavingAccount, BigDecimal.valueOf(100)));
        }};
        currentAccountDto = new AccountDto(1, EnumAccountType.CurrentAccount, BigDecimal.valueOf(10));
        currentAccount = new CurrentAccount(currentAccountDto,accountTypeList.get(0));
        currentAccount.setId(1);
    }

    @Test
    void createAccount() {
        // Arrange
        Mockito.when(mockAccountFactory.createAccount(Mockito.any(AccountDto.class))).thenReturn(currentAccount);
        Mockito.when(mockAccountRepository.save(Mockito.any(Account.class))).thenReturn(currentAccount);
        //Action
        currentAccount = accountService.createAccount(currentAccountDto);

        //Assert
        assertEquals(1,currentAccount.getId() );
        assertInstanceOf(String.class,currentAccount.getAccountNumber().number);
        //assertEquals( currentAccountDto.getInitialCredit() ,currentAccount.getBalance());

    }
}