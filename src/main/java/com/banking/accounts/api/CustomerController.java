package com.banking.accounts.api;

import com.banking.accounts.application.service.ICustomerAppService;
import com.banking.accounts.domain.customer.dto.CustomerAccountsDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@RestController
@RequestMapping(path="/api/customer")
public class CustomerController {
    private final ICustomerAppService customerAppService;
    public CustomerController(ICustomerAppService customerAppService) {
        this.customerAppService = customerAppService;
    }

    @GetMapping("get")
    public ResponseEntity<CustomerAccountsDto> getCustomerAccounts(@Valid @RequestParam Integer customerId)  {
        CustomerAccountsDto customerAccountsDto = customerAppService.getCustomerAccounts(customerId);
        return new ResponseEntity<>(customerAccountsDto,HttpStatus.OK);
    }
}
