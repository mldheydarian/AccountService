package com.banking.accounts.domain.account.entity;

import com.banking.accounts.domain.account.dto.AccountDto;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

import java.util.Date;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer id;

    protected String accountNumber;
    protected BigDecimal initialCredit;
    @NotNull
    protected BigDecimal Balance;
    protected AccountStatus status;
    protected String email;   // Todo: change to VO
    protected Date createdDate;
    protected Date birthDate;
    protected Integer customerId;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "account_type_id")
    private AccountType accountType;

    public Account(AccountDto accountDto)
    {
        customerId = accountDto.getCustomerId();
        initialCredit= accountDto.getInitialCredit();
        email= accountDto.getEmail();
        birthDate= accountDto.getBirthDate();
        accountType = new AccountType(accountDto.getEnumAccountType());
        setBalance(BigDecimal.ZERO);
    }


    public abstract void updateBalance(BigDecimal newAmount);



}
