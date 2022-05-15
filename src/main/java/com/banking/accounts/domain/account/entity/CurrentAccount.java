package com.banking.accounts.domain.account.entity;

import com.banking.accounts.domain.account.dto.AccountDto;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@NoArgsConstructor
public class CurrentAccount extends Account {

    public CurrentAccount(AccountDto accountDto) {
        super(accountDto);
        this.accountNumber = new AccountNumber(EnumAccountType.CurrentAccount);
        //System.out.println("New Current Account Added");
    }

    @Override
    public void updateBalance(BigDecimal newAmount) {
        this.Balance = this.Balance.add(newAmount) ;
    }



}
