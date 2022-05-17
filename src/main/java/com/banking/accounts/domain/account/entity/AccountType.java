package com.banking.accounts.domain.account.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class AccountType {
    @JsonIgnore
    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    private EnumAccountType enumAccountType;

    private BigDecimal minBalance;


    @JsonIgnore
    @OneToMany(mappedBy = "accountType", fetch = FetchType.LAZY)
    private List<Account> accounts;


    public AccountType(Long id, EnumAccountType enumAccountType, BigDecimal minBalance) {
        this.id = id;
        this.enumAccountType = enumAccountType;
        this.minBalance = minBalance;
    }
}
