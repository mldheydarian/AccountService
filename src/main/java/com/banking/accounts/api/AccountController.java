package com.banking.accounts.api;

import com.banking.accounts.application.service.IAccountAppService;
import com.banking.accounts.domain.account.service.IAccountService;
import com.banking.accounts.domain.account.dto.AccountDto;
import com.banking.accounts.domain.account.entity.Account;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.Valid;


@RestController
@RequestMapping(path="/api/account")
public class AccountController {
    private final IAccountAppService accountAppService;
    final Logger logger;

    public AccountController(IAccountAppService accountAppService, Logger logger) {
        this.accountAppService = accountAppService;
        this.logger = logger;
    }

    @PostMapping("add")
    public ResponseEntity<Account> addAccount(@Valid @RequestBody AccountDto accountDto )  {
        logger.info("Account API Request: AccountDto : "+ accountDto.toString());
        Account newAccount = accountAppService.createAccount(accountDto);
        return new ResponseEntity<>(newAccount,HttpStatus.OK);
    }
}
