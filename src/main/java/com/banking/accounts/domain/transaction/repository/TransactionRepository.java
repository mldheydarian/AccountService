package com.banking.accounts.domain.transaction.repository;

import com.banking.accounts.domain.account.entity.Account;
import com.banking.accounts.domain.transaction.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {


}

