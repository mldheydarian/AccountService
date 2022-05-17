package com.banking.accounts.domain.account.repository;

import com.banking.accounts.domain.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Integer> {

    List<Account> findByCustomerId(@NotNull Integer customerId);
}

