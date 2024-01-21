package com.example.BankingApplication.service;

import com.example.BankingApplication.model.TransactionTypeEntity;
import com.example.BankingApplication.repository.TransactionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionTypeService {

    @Autowired
    TransactionTypeRepository transactionTypeRepository;

    public List<TransactionTypeEntity> getAllTransactionType() {
        return transactionTypeRepository.findAll();
    }

    public TransactionTypeEntity getTransactionTypeById(Integer id) {
        return transactionTypeRepository.findById(id).orElse(null);
    }

    public void addTransactionType(TransactionTypeEntity transactionType) {
        transactionTypeRepository.save(transactionType);
    }

    public void updateTransactionType(TransactionTypeEntity transactionType) {
        transactionTypeRepository.save(transactionType);
    }

    public void deleteTransactionType(Integer id) {
        transactionTypeRepository.deleteById(id);
    }


}
