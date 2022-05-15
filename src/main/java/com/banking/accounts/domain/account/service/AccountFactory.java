package com.banking.accounts.domain.account.service;

import com.banking.accounts.domain.account.dto.AccountDto;
import com.banking.accounts.domain.account.entity.Account;
import com.banking.accounts.domain.account.entity.CurrentAccount;
import com.banking.accounts.domain.account.entity.SavingAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountFactory implements IAccountFactory {


    public Account createAccount(AccountDto accountDto) {
        switch (accountDto.getEnumAccountType()) {
            case CurrentAccount:
                return new CurrentAccount(accountDto);
            case SavingAccount:
                return new SavingAccount(accountDto);
        }
        return null; // TODO: throw exception
    }
}
