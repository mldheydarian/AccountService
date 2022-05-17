package com.banking.accounts.domain.account.entity;

import com.banking.accounts.domain.account.dto.AccountDto;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
public class SavingAccount extends Account {

    protected BigDecimal withdrawLimit;
    protected Double interestRate;

    public SavingAccount(AccountDto accountDto, AccountType accountType) {
        super(accountDto);
        this.accountType = accountType;
        this.accountNumber = new AccountNumber(EnumAccountType.SavingAccount);
    }

    @Override
    public void updateBalance(BigDecimal newAmount) {

        this.Balance = this.Balance.add(newAmount) ;
    }



}
