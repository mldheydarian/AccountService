package com.banking.accounts.application;

import com.banking.accounts.domain.account.service.IAccountService;
import com.banking.accounts.domain.account.dto.AccountDto;
import com.banking.accounts.domain.account.entity.Account;
import com.banking.accounts.domain.transaction.service.ITransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
@RequestMapping(path="/api/account")
public class AccountController {
    private final IAccountService accountService;

    public AccountController(IAccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("add")
    public ResponseEntity<Account> addAccount(@RequestBody AccountDto accountDto )  {

        Account newAccount = accountService.createAccount(accountDto);
        return new ResponseEntity<>(newAccount,HttpStatus.OK);
    }
}
