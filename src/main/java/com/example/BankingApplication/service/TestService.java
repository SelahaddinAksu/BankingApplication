package com.example.BankingApplication.service;

import com.example.BankingApplication.model.TestEntity;
import com.example.BankingApplication.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TestService {
    @Autowired
    TestRepository testRepository;

    public List<TestEntity> getAllTest() {
        System.out.println("servis");
        return testRepository.findAll();
    }
    public TestEntity getById(int id) {
        return testRepository.findById(id).orElse(null);
    }
    @Transactional
    public void addTest(TestEntity testEntity){
        System.out.println("post servis");
        testRepository.save(testEntity);


    }

}
