package com.example.BankingApplication.controller;

import com.example.BankingApplication.model.AccountEntity;
import com.example.BankingApplication.model.TransactionLogEntity;
import com.example.BankingApplication.service.AccountService;
import com.example.BankingApplication.service.BankService;
import com.example.BankingApplication.service.TransactionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionsController {

    @Autowired
    BankService bankService;
    @Autowired
    TransactionLogService transactionLogService;
    @Autowired
    AccountController accountController;
    @Autowired
    AccountService accountService;
    TransactionLogEntity transactionLog;
    LocalDateTime today = LocalDateTime.now();
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @PostMapping("/addNewAccount")
    public void addNewAccount(@RequestBody AccountEntity account) {
        accountController.addAccount(account);
    }

    @PutMapping("/depositMoney/{accountId}")
    public void depositMoney(@PathVariable AccountEntity accountId, Double transactionAmount) throws Exception {

        AccountEntity account = accountService.getAccountById(accountId);

        if (account != null) {
            if (transactionAmount >= 0) {
                transactionLog.setAccountId(accountId.getId());
                transactionLog.setTransactionAmount(transactionAmount);
                transactionLog.setTransactionDate(Date.valueOf(dtf.format(today)));
                transactionLog.setBankId(account.getBankId());
                transactionLog.setTransactionType("Deposit Money");
                Double totalAmount = account.getTotalAmount() + transactionAmount;
                transactionLog.setTotalAmount(totalAmount);
                transactionLogService.addTransactionLog(transactionLog);

                account.setTotalAmount(totalAmount);
                accountService.updateAccount(account);
            } else {
                throw new IllegalArgumentException("Transaction amount must be greater than 0 and cannot be left blank");
            }
        } else {
            throw new NullPointerException("There are no accounts for this submitted account_id.");
        }
    }

    @PutMapping("/withdrawMoney/{accountId}")
    public void withdrawMoney(@PathVariable AccountEntity accountId, Double transactionAmount) {

        AccountEntity account = accountService.getAccountById(accountId);

        if (account != null) {
            if (transactionAmount >= 0) {
                transactionLog.setAccountId(accountId.getId());
                transactionLog.setTransactionAmount(transactionAmount);
                transactionLog.setTransactionDate(Date.valueOf(dtf.format(today)));
                transactionLog.setBankId(account.getBankId());
                transactionLog.setTransactionType("Deposit Money");
                Double totalAmount = account.getTotalAmount() - transactionAmount;
                if (totalAmount < 0) {
                    transactionLog.setTotalAmount(totalAmount);
                    transactionLogService.addTransactionLog(transactionLog);

                    account.setTotalAmount(totalAmount);
                    accountService.updateAccount(account);
                } else {
                    throw new IllegalArgumentException("Transaction amount cannot be more than the total balance");
                }
            } else {
                throw new IllegalArgumentException("Transaction amount must be greater than 0 and cannot be left blank");
            }
        } else {
            throw new NullPointerException("There are no accounts for this submitted account_id.");
        }
    }

    @GetMapping("/totalAmountQuery/{accountId}")
    public AccountEntity totalAmountQuery(@PathVariable AccountEntity accountId) {
        return transactionLogService.getAmount(accountId);
    }

    @GetMapping("/TarihceSorgula/{id}")
    public List<TransactionLogEntity> getTarihçeSorgula(@PathVariable AccountEntity id) {
        return transactionLogService.getHistoryLog(id);
    }

    @GetMapping("/TarihceSorgula")
    public List<TransactionLogEntity> getTarihçeSorgulaAll() {
        return transactionLogService.getHistoryLogAll();
    }
}
    

