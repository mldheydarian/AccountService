package com.banking.accounts.domain.customer.service;

import com.banking.accounts.domain.customer.entity.Customer;
import com.banking.accounts.domain.customer.repository.ICustomerRepository;
import com.banking.accounts.domain.transaction.dto.TransactionDto;
import com.banking.accounts.domain.transaction.entity.EnumTransactionStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class CustomerServiceTest {

    @MockBean
    ICustomerRepository customerRepository;

    ICustomerService customerService;
    Optional<Customer>  customer;
    @BeforeEach
    void setUp() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1991, 9, 21 );
        customerService = new CustomerService(customerRepository) ;
        customer = Optional.of(new Customer(1, "Arman", "Heydarian", "arman.heydarian@gmail.com", calendar.getTime()));
    }

    @Test
    void getCustomerById() {
        //Arrange
        Mockito.when(customerRepository.findById(Mockito.any(Integer.class))).thenReturn(customer);
        //Action
        customer = customerService.getCustomerById(1);
        //Assert
        assertTrue(customer.isPresent());
        assertEquals(1,customer.get().getId());

    }
}