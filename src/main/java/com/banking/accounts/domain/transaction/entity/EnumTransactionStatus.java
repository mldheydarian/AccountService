package com.banking.accounts.domain.transaction.entity;

public enum EnumTransactionStatus {
    Posted,   //Sent into the System
    Confirmed,  // Validated and Confirmed by bank
    Disputed  //Cancelled

}
