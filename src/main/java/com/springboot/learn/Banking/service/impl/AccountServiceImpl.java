package com.springboot.learn.Banking.service.impl;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.springboot.learn.Banking.dto.AccountDto;
import com.springboot.learn.Banking.entity.Account;
import com.springboot.learn.Banking.mapper.AccountMapper;
import com.springboot.learn.Banking.repository.AccountRepository;
import com.springboot.learn.Banking.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{

    private AccountRepository accountRepository;
    


    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {

        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
        
    }


    @Override
    public AccountDto getAccountById(Long id) {

        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("account is not exist"));

        return AccountMapper.mapToAccountDto(account);
        
    }

    @Override
    public AccountDto deposit(Long id, double amount) {
        
        Account account = accountRepository.findById(id)
             .orElseThrow(() -> new RuntimeException("account does not exist"));

             double total = account.getBalance() + amount;
             account.setBalance(total);
             Account savedAccount = accountRepository.save(account);
             return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {

        
        Account account = accountRepository.findById(id)
             .orElseThrow(() -> new RuntimeException("account does not exist"));

             if(account.getBalance() < amount){
    
                throw new RuntimeException("insuficiant amount");
             }

             double total = account.getBalance() - amount;
             account.setBalance(total);
             Account savedAccount = accountRepository.save(account);
             return AccountMapper.mapToAccountDto(savedAccount);
       
    }

    @Override
    public List<AccountDto> getAllAcount() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map((account) -> AccountMapper.mapToAccountDto(account))
              .collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long id) {
        
        Account account = accountRepository.findById(id)
             .orElseThrow(() -> new RuntimeException("account does not exist"));

             accountRepository.deleteById(id);
    }

    
    
}
