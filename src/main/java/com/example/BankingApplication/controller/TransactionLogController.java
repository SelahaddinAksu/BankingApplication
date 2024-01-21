package com.example.BankingApplication.controller;

import com.example.BankingApplication.model.AccountEntity;
import com.example.BankingApplication.model.TransactionLogEntity;
import com.example.BankingApplication.model.TransactionTypeEntity;
import com.example.BankingApplication.service.AccountService;
import com.example.BankingApplication.service.BankService;
import com.example.BankingApplication.service.TransactionLogService;
import com.example.BankingApplication.service.TransactionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/transactionLog")
public class TransactionLogController {

    @Autowired
    BankService bankService;
    @Autowired
    TransactionLogService transactionLogService;
    @Autowired
    AccountController accountController;

    @PostMapping("/yeniHesapEkle")
    public void addYeniHesapEkle(@RequestBody AccountEntity account) {
        accountController.addAccount(account);
    }

    @PutMapping("/paraYatir/{id}")
    public void addParaYatir(@PathVariable AccountEntity id, @RequestBody TransactionLogEntity transactionLog) {
        AccountEntity existing = transactionLogService.getAmount(id);
        LocalDateTime today = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if (existing != null) {
            transactionLog.setAccountId(transactionLog.getAccountId());
            transactionLog.setTransactionDate(Date.valueOf(dtf.format(today)));
            transactionLog.setTransactionAmount(transactionLog.getTransactionAmount());
            transactionLog.setTransactionType(transactionLog.getTransactionType());
            Double totalAmount = transactionLog.getTotalAmount() + transactionLog.getTransactionAmount();
            transactionLog.setTotalAmount(totalAmount);
            transactionLogService.addTransactionLog(transactionLog);

            existing.setTotalAmount(totalAmount);
            transactionLogService.updateTotalAmount(existing);
        }else {
            System.out.println("İşlem yapmak istediğiniz hesap bulunamadı...");
        }
    }

    @PutMapping("/paraCek/{id}")
    public void updateParaCek(@PathVariable AccountEntity id, @RequestBody TransactionLogEntity transactionLog) {
        AccountEntity existing = transactionLogService.getAmount(id);
        LocalDateTime today = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if (existing != null) {
            transactionLog.setAccountId(transactionLog.getAccountId());
            transactionLog.setTransactionDate(Date.valueOf(dtf.format(today)));
            transactionLog.setTransactionAmount(transactionLog.getTransactionAmount());
            transactionLog.setTransactionType(transactionLog.getTransactionType());
            Double totalAmount = transactionLog.getTotalAmount() - transactionLog.getTransactionAmount();
            if (totalAmount > 0) {
                transactionLog.setTotalAmount(totalAmount);
                transactionLogService.addTransactionLog(transactionLog);

                existing.setTotalAmount(totalAmount);
                transactionLogService.updateTotalAmount(existing);
            } else {
                System.out.println("Yetersiz hesap bakiyesi");
            }
        }else {
            System.out.println("İşlem yapmak istediğiniz hesap bulunamadı...");
        }
    }

    @GetMapping("/BakiyeSorgula/{id}")
    public AccountEntity getBakiyeSorgula(@PathVariable AccountEntity id) {
        return transactionLogService.getAmount(id);
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
    

