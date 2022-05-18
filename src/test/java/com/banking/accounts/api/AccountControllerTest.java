package com.banking.accounts.api;

import com.banking.accounts.application.service.IAccountAppService;
import com.banking.accounts.domain.account.dto.AccountDto;
import com.banking.accounts.domain.account.entity.Account;
import com.banking.accounts.domain.account.entity.AccountType;
import com.banking.accounts.domain.account.entity.CurrentAccount;
import com.banking.accounts.domain.account.entity.EnumAccountType;
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
import java.util.ArrayList;
import java.util.Calendar;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc

@RunWith(SpringRunner.class)
@SpringBootTest()
class AccountControllerTest {

    @MockBean
    IAccountAppService accountAppService;
    @Autowired
    MockMvc mockMvc;
    AccountDto currentAccountDto;
    Account currentAccount;

    @BeforeEach
    void setUp() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1991, 9, 21 );
        ArrayList<AccountType> accountTypeList = new ArrayList<AccountType>() {{
            add(new AccountType((long) 1, EnumAccountType.CurrentAccount, BigDecimal.valueOf(100)));
            add(new AccountType((long) 2, EnumAccountType.SavingAccount, BigDecimal.valueOf(10)));
        }};
        currentAccountDto = new AccountDto(1, EnumAccountType.CurrentAccount, BigDecimal.valueOf(10),"Arman.heydarian@gmail.com", calendar.getTime());
        currentAccount = new CurrentAccount(currentAccountDto,accountTypeList.get(0));
        currentAccount.setId(1);
        currentAccount.setBalance(BigDecimal.valueOf(10));
    }

    @Test
    void addAccount() throws Exception {
        //Arrange
        ObjectMapper objectMapper = new ObjectMapper();
        Mockito.when(accountAppService.createAccount(Mockito.any(AccountDto.class))).thenReturn(currentAccount);
        //Action and Assert
        mockMvc.perform(post("/api/account/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(currentAccountDto)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("Active"))
                .andExpect(jsonPath("$.customerId").value(currentAccountDto.getCustomerId()))
                .andExpect(jsonPath("$.balance").value(currentAccountDto.getInitialCredit()));

    }
}