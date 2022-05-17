package com.banking.accounts.domain.customer.service;

import com.banking.accounts.application.exception.RecordNotFoundException;
import com.banking.accounts.domain.customer.entity.Customer;
import com.banking.accounts.domain.customer.repository.ICustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService implements ICustomerService {
    final ICustomerRepository customerRepository;

    public Optional<Customer> getCustomerById(Integer customerId)
    {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isPresent())
            return customer;
        else
            throw  new RecordNotFoundException("The Customer not found in DB");
    }

}
