package com.example.BankingApplication.controller;

import com.example.BankingApplication.model.BankEntity;
import com.example.BankingApplication.model.TransactionTypeEntity;
import com.example.BankingApplication.service.TransactionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactionType")
public class TransactionTypeController {

    @Autowired
    TransactionTypeService transactionTypeService;

    @GetMapping
    public List<TransactionTypeEntity> getAllTransactionType() {
        return transactionTypeService.getAllTransactionType();
    }

    @GetMapping("/get/{id}")
    public TransactionTypeEntity getTransactionTypeById(@PathVariable Integer id) {
        return transactionTypeService.getTransactionTypeById(id);
    }

    @PostMapping
    public void addTransactionType(@RequestBody TransactionTypeEntity transactionTypeEntity) {
        transactionTypeService.addTransactionType(transactionTypeEntity);
    }

    @PutMapping("/update/{id}")
    public void updateTransactionType(@PathVariable Integer id, @RequestBody TransactionTypeEntity transactionType) {
        TransactionTypeEntity existing= transactionTypeService.getTransactionTypeById(id);
        if (existing != null) {
            existing.setId(transactionType.getId());
            existing.setTransactionType(transactionType.getTransactionType());
            transactionTypeService.updateTransactionType(existing);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTransactionType(@PathVariable Integer id) {
        transactionTypeService.deleteTransactionType(id);
    }

}
    

