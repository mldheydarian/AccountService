package com.banking.accounts.domain.account.entity;

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
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private EnumAccountType enumAccountType;

    private BigDecimal minBalance;

    @OneToMany(mappedBy = "accountType" ,cascade = CascadeType.ALL)
    private List<Account> accounts;

    public AccountType(EnumAccountType enumAccountType) {
        this.enumAccountType = enumAccountType;
    }

}
