package com.banking.accounts.domain.account.entity;

import com.banking.accounts.domain.account.dto.AccountDto;
import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer id;

    @NotNull
    protected AccountNumber accountNumber;

    @NotNull
    @DecimalMin(value = "0.0")
    protected BigDecimal initialCredit;

    @NotNull
    @DecimalMin(value = "0.0")
    protected BigDecimal Balance;

    protected AccountStatus status;
    protected Date createdDate;

    @NotNull
    protected Integer customerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_type_id")
    protected AccountType accountType;

    public Account(AccountDto accountDto)
    {
        customerId = accountDto.getCustomerId();
        initialCredit= accountDto.getInitialCredit();
        status = AccountStatus.Active;
        createdDate = new Date();
        setBalance(BigDecimal.ZERO);
    }


    public abstract void updateBalance(BigDecimal newAmount);



}
