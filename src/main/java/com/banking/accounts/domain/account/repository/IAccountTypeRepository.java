package com.banking.accounts.domain.account.repository;

import com.banking.accounts.domain.account.entity.Account;
import com.banking.accounts.domain.account.entity.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAccountTypeRepository extends JpaRepository<AccountType, Integer> {
}

