package com.example.BankingApplication.controller;

import com.example.BankingApplication.model.AccountEntity;
import com.example.BankingApplication.model.BankEntity;
import com.example.BankingApplication.service.AccountService;
import com.example.BankingApplication.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping
    public List<AccountEntity> getAllAccount() {
        return accountService.getAllAccount();
    }

    @GetMapping("/get/{id}")
    public AccountEntity getAccountById(@PathVariable Integer id) {
        return accountService.getAccountById(id);
    }

    @PostMapping
    public void addAccount(@RequestBody AccountEntity account) {
        accountService.addAccount(account);
    }

    @PutMapping("/update/{id}")
    public void updateAccount(@PathVariable Integer id, @RequestBody AccountEntity account) {
        AccountEntity existing= accountService.getAccountById(id);
        if (existing != null) {
            existing.setId(account.getId());
            existing.setAccountHolder(account.getAccountHolder());
            existing.setBankId(account.getBankId());
            existing.setTotalAmount(account.getTotalAmount());
            accountService.updateAccount(existing);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAccount(@PathVariable Integer id) {
        accountService.deleteAccount(id);
    }

}
    

