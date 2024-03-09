package com.springboot.learn.Banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.learn.Banking.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{
    
}
