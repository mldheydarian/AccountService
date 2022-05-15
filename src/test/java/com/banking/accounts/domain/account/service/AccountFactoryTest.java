package com.banking.accounts.domain.account.service;

import com.banking.accounts.domain.account.dto.AccountDto;
import com.banking.accounts.domain.account.entity.CurrentAccount;
import com.banking.accounts.domain.account.entity.EnumAccountType;
import com.banking.accounts.domain.account.entity.SavingAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import java.math.BigDecimal;
import java.util.Calendar;


class AccountFactoryTest {

    IAccountFactory accountFactory;
    AccountDto currentAccountDto;
    AccountDto savingAccountDto;


    @BeforeEach
    void beforeAll() {
        accountFactory =new AccountFactory();
        Calendar calendar = Calendar.getInstance();
        calendar.set(1991, 9, 21 );
        currentAccountDto = new AccountDto(1, EnumAccountType.CurrentAccount, BigDecimal.valueOf(10),"Arman.heydarian@gmail.com", calendar.getTime());
        savingAccountDto = new AccountDto(1, EnumAccountType.SavingAccount, BigDecimal.valueOf(10),"Arman.heydarian@gmail.com", calendar.getTime());

    }

    @Test
    void createCurrentAccount() {
        assertInstanceOf(CurrentAccount.class , accountFactory.createAccount(currentAccountDto));
    }
    @Test
    void createDepositAccount() {
        assertInstanceOf(SavingAccount.class , accountFactory.createAccount(savingAccountDto));
    }

}
