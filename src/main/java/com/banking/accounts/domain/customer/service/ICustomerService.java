package com.banking.accounts.domain.customer.service;

import com.banking.accounts.domain.customer.entity.Customer;

import java.util.Optional;

public interface ICustomerService {
    Optional<Customer> getCustomerById(Integer customerId) ;
}
