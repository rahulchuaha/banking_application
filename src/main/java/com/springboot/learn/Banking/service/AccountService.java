package com.springboot.learn.Banking.service;

import java.util.List;

import com.springboot.learn.Banking.dto.AccountDto;

public interface AccountService {
    
    AccountDto createAccount(AccountDto accountDto);

    AccountDto getAccountById(Long id);

    AccountDto deposit(Long id, double balance);

    AccountDto withdraw(Long id, double amount);

    List<AccountDto> getAllAcount();

    void deleteAccount(Long id);
}
