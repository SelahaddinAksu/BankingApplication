package com.example.BankingApplication.controller;

import com.example.BankingApplication.model.BankEntity;
import com.example.BankingApplication.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank")
public class BankController {

    @Autowired
    BankService bankService;

    @GetMapping
    public List<BankEntity> getAllBank() {
        return bankService.getAllBank();
    }

    @GetMapping("/get/{id}")
    public BankEntity getBankById(@PathVariable Integer id) {
        return bankService.getBankById(id);
    }

    @PostMapping
    public void addBank(@RequestBody BankEntity bank) {
        bankService.addBank(bank);
    }

    @PutMapping("/update/{id}")
    public void updateBank(@PathVariable Integer id, @RequestBody BankEntity bank) {
        BankEntity existing= bankService.getBankById(id);
        if (existing != null) {
            existing.setBankName(bank.getBankName());
            bankService.updateBank(existing);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBank(@PathVariable Integer id) {
        bankService.deleteBank(id);
    }

}
    

