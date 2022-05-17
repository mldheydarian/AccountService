package com.banking.accounts.domain.customer.repository;

import com.banking.accounts.domain.account.entity.Account;
import com.banking.accounts.domain.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Integer> {

}

