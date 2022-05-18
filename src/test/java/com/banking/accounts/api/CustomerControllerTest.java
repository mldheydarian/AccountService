package com.banking.accounts.api;

import com.banking.accounts.application.service.IAccountAppService;
import com.banking.accounts.application.service.ICustomerAppService;
import com.banking.accounts.domain.account.dto.AccountDto;
import com.banking.accounts.domain.account.dto.AccountOutputDto;
import com.banking.accounts.domain.account.entity.*;
import com.banking.accounts.domain.customer.dto.CustomerAccountsDto;
import com.banking.accounts.domain.customer.entity.Customer;
import com.banking.accounts.domain.customer.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.math.BigDecimal;
import java.util.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest()
class CustomerControllerTest {


    @MockBean
    ICustomerAppService customerAppService;
    @Autowired
    MockMvc mockMvc;
    CustomerAccountsDto customerAccountsDto;
    Account currentAccount;
    Optional<Customer> customer;
    @BeforeEach
    void setUp() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1991, 9, 21 );
        customer = Optional.of(new Customer(1, "Arman", "Heydarian", "arman.heydarian@gmail.com", calendar.getTime()));
        AccountOutputDto accountOutputDto = new AccountOutputDto(BigDecimal.valueOf(100), AccountStatus.Active, EnumAccountType.CurrentAccount);
        customerAccountsDto =new CustomerAccountsDto(customer.get(), new ArrayList<AccountOutputDto>() {{add(accountOutputDto);}});
    }

    @Test
    void getCustomerAccounts() throws Exception {
        //Arrange
        ObjectMapper objectMapper = new ObjectMapper();
        Mockito.when(customerAppService.getCustomerAccounts(Mockito.any(Integer.class))).thenReturn(customerAccountsDto);
        //Action and Assert
        mockMvc.perform(get("/api/customer/get")
                        .param("customerId", "1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.customerInformation.name").value(customer.get().getName()));

    }
}