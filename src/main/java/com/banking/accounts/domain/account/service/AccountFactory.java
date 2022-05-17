package com.banking.accounts.domain.account.service;

import com.banking.accounts.application.exception.RecordNotFoundException;
import com.banking.accounts.domain.account.dto.AccountDto;
import com.banking.accounts.domain.account.entity.*;
import com.banking.accounts.domain.account.repository.IAccountTypeRepository;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.annotation.PostConstruct;
import java.util.List;

@Service

public class AccountFactory implements IAccountFactory  {

    public AccountFactory(IAccountTypeRepository accountTypeRepository) {
        this.accountTypeRepository = accountTypeRepository;
    }
    private final IAccountTypeRepository accountTypeRepository;


    public Account createAccount(AccountDto accountDto) {

        List<AccountType> accountTypeList = accountTypeRepository.findAll();
        switch (accountDto.getEnumAccountType()) {
            case CurrentAccount:
                AccountType currentAccountType  = accountTypeList.stream()
                        .filter(accountType -> EnumAccountType.CurrentAccount.equals(accountType.getEnumAccountType()))
                        .findAny()
                        .orElse(null);
                return new CurrentAccount(accountDto,currentAccountType);

            case SavingAccount:
                AccountType savingAccountType = accountTypeList.stream()
                        .filter(accountType -> EnumAccountType.SavingAccount.equals(accountType.getEnumAccountType()))
                        .findAny()
                        .orElse(null);
                return new SavingAccount(accountDto, savingAccountType);

        }
        throw new RecordNotFoundException("This Type of Account Is not Ready now");
    }
}
