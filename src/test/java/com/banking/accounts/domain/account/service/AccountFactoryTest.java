package com.banking.accounts.domain.account.service;

import com.banking.accounts.domain.account.dto.AccountDto;
import com.banking.accounts.domain.account.entity.*;
import com.banking.accounts.domain.account.repository.IAccountRepository;
import com.banking.accounts.domain.account.repository.IAccountTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


class AccountFactoryTest {

    @MockBean
    IAccountTypeRepository mockAccountTypeRepository;

    IAccountFactory accountFactory;
    AccountDto currentAccountDto;
    AccountDto savingAccountDto;
    private List<AccountType> accountTypeList;

    @BeforeEach
    void setUp() {
        accountFactory = new AccountFactory(mockAccountTypeRepository);
        Calendar calendar = Calendar.getInstance();
        calendar.set(1991, 9, 21 );
        currentAccountDto = new AccountDto(1, EnumAccountType.CurrentAccount, BigDecimal.valueOf(10),"Arman.heydarian@gmail.com", calendar.getTime());
        savingAccountDto = new AccountDto(1, EnumAccountType.SavingAccount, BigDecimal.valueOf(10),"Arman.heydarian@gmail.com", calendar.getTime());
        currentAccountDto = new AccountDto(1, EnumAccountType.CurrentAccount, BigDecimal.valueOf(10),"Arman.heydarian@gmail.com", calendar.getTime());
        accountTypeList = new ArrayList<AccountType>(){{
             add(new AccountType((long)1,EnumAccountType.CurrentAccount,BigDecimal.valueOf(100)));
             add(new AccountType((long)2,EnumAccountType.SavingAccount,BigDecimal.valueOf(100)));
        }};
    }

    @Test
    void createCurrentAccount() {
        Mockito.when(mockAccountTypeRepository.findAll()).thenReturn(accountTypeList);
        assertInstanceOf(CurrentAccount.class , accountFactory.createAccount(currentAccountDto));
    }
    @Test
    void createDepositAccount() {
        Mockito.when(mockAccountTypeRepository.findAll()).thenReturn(accountTypeList);
        assertInstanceOf(SavingAccount.class , accountFactory.createAccount(savingAccountDto));
    }

}
