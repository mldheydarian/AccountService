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

    public SavingAccount(AccountDto accountDto) {
        super(accountDto);
        System.out.println("New Saving Account Added");
    }

    @Override
    public void updateBalance(BigDecimal newAmount) {
        this.Balance = this.Balance.add(newAmount) ;
    }



}
