package com.banking.accounts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Currency;
import java.util.Locale;


@SpringBootApplication
@EnableSwagger2
public class AccountApplication {


    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);

    }

}
