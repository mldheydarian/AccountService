package com.banking.accounts.integration;

import com.banking.accounts.AccountApplication;
import com.banking.accounts.domain.customer.dto.CustomerAccountsDto;
import com.banking.accounts.domain.customer.entity.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Calendar;
import java.util.Optional;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest( classes = AccountApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
class CustomerIntegrationTest {

    @Autowired
    MockMvc mockMvc;
    CustomerAccountsDto customerAccountsDto;
    Optional<Customer> customer;
    @BeforeEach
    void setUp() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1991, 9, 21 );
        customer = Optional.of(new Customer(1, "Arman", "Heydarian", "arman.heydarian@gmail.com", calendar.getTime()));
        //AccountOutputDto accountOutputDto = new AccountOutputDto(BigDecimal.valueOf(100), AccountStatus.Active, EnumAccountType.CurrentAccount);
        //customerAccountsDto =new CustomerAccountsDto(customer.get(), new ArrayList<AccountOutputDto>() {{add(accountOutputDto);}});
    }

    @Test
    void getCustomerAccounts() throws Exception {
        //Action and Assert
        mockMvc.perform(get("/api/customer/get")
                        .param("customerId", "1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.customerInformation.name").value(customer.get().getName()));

    }
}