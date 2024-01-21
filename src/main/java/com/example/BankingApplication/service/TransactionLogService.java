package com.example.BankingApplication.service;

import com.example.BankingApplication.model.AccountEntity;
import com.example.BankingApplication.model.TransactionLogEntity;
import com.example.BankingApplication.model.TransactionTypeEntity;
import com.example.BankingApplication.repository.AccountRepository;
import com.example.BankingApplication.repository.TransactionLogRepository;
import com.example.BankingApplication.repository.TransactionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransactionLogService {
    @Autowired
    TransactionLogRepository transactionLogRepository;
    @Autowired
    AccountRepository accountRepository;

    @Transactional
    public void addTransactionLog(TransactionLogEntity transactionLog) {
        transactionLogRepository.save(transactionLog);
    }

    public TransactionLogEntity getTransactionLogAccountId(int id) {
        return transactionLogRepository.findByAccountId(id);
    }

    public List<TransactionLogEntity> getHistoryLog(Integer id) {
        return transactionLogRepository.findTransactionLogByAccountId(id);
    }

    public List<TransactionLogEntity> getHistoryLogAll() {
        return transactionLogRepository.findAll();
    }

    @Transactional
    public void updateTotalAmount(AccountEntity totalAmount) {
        accountRepository.save(totalAmount);
    }

    public AccountEntity getAmount(int id) {
        return accountRepository.findById(id).orElse(null);
    }

}
