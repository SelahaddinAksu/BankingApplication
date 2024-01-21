package com.example.BankingApplication.service;

import com.example.BankingApplication.model.BankEntity;
import com.example.BankingApplication.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankService {

    @Autowired
    BankRepository bankRepository;

    public List<BankEntity> getAllBank() {
        return bankRepository.findAll();
    }

    public BankEntity getBankById(Integer id) {
        return bankRepository.findById(id).orElse(null);
    }

    public void addBank(BankEntity bank) {
        bankRepository.save(bank);
    }

    public void updateBank(BankEntity bank) {
        bankRepository.save(bank);
    }

    public void deleteBank(Integer id) {
        bankRepository.deleteById(id);
    }


}
