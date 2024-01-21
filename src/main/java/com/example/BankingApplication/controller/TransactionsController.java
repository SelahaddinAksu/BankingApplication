package com.example.BankingApplication.controller;

import com.example.BankingApplication.model.AccountEntity;
import com.example.BankingApplication.model.TransactionLogEntity;
import com.example.BankingApplication.service.AccountService;
import com.example.BankingApplication.service.BankService;
import com.example.BankingApplication.service.TransactionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

    @PostMapping("/addNewAccount")
    public void addNewAccount(@RequestBody AccountEntity account) {
        accountController.addAccount(account);
    }

    @PutMapping("/test/{transactionAmount}")
    public void depositMoneytest(@PathVariable("accountId") int accountId, @PathVariable("transactionAmount") Double transactionAmount) throws Exception {
        System.out.println("test başarılı");
    }

        @PutMapping("/depositMoney/{accountId}/{transactionAmount}")
    public void depositMoney(@PathVariable("accountId") int accountId, @PathVariable("transactionAmount") Double transactionAmount) throws Exception {

        AccountEntity account = accountService.getAccountById(accountId);
        TransactionLogEntity transactionLog = new TransactionLogEntity();

        if (account != null) {
            if (transactionAmount >= 0) {
                transactionLog.setAccountId(accountId);
                transactionLog.setTransactionAmount(transactionAmount);
                transactionLog.setTransactionDate(new Date(System.currentTimeMillis()));
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

    @PutMapping("/withdrawMoney/{accountId}/{transactionAmount}")
    public void withdrawMoney(@PathVariable("accountId") int accountId, @PathVariable("transactionAmount") Double transactionAmount) {

        AccountEntity account = accountService.getAccountById(accountId);
        TransactionLogEntity transactionLog = transactionLogService.getTransactionLogAccountId(accountId);

        if (account != null) {
            if (transactionAmount >= 0) {
                transactionLog.setAccountId(accountId);
                transactionLog.setTransactionAmount(transactionAmount);
                transactionLog.setTransactionDate(new Date(System.currentTimeMillis()));
                transactionLog.setBankId(account.getBankId());
                transactionLog.setTransactionType("Withdraw Money");
                Double totalAmount = account.getTotalAmount() - transactionAmount;
                if (totalAmount > 0) {
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
    public AccountEntity totalAmountQuery(@PathVariable int accountId) {
        return transactionLogService.getAmount(accountId);
    }

    @GetMapping("/history/{id}")
    public List<TransactionLogEntity> history(@PathVariable int id) {
        return transactionLogService.getHistoryLog(id);
    }

    @GetMapping("/historyAll")
    public List<TransactionLogEntity> historyAll() {
        return transactionLogService.getHistoryLogAll();
    }
}
    

