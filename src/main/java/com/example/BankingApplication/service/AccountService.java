package com.example.BankingApplication.service;

import com.example.BankingApplication.model.AccountEntity;
import com.example.BankingApplication.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    public List<AccountEntity> getAllAccount() {
        return accountRepository.findAll();
    }

    public AccountEntity getAccountById(Integer id) {
        return accountRepository.findById(id).orElse(null);
    }
    public void addAccount(AccountEntity account) {
        accountRepository.save(account);
    }

    public void updateAccount(AccountEntity account) {
        accountRepository.save(account);
    }

    public void deleteAccount(Integer id) {
        accountRepository.deleteById(id);
    }

}
