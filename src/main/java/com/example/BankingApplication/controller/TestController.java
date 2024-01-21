package com.example.BankingApplication.controller;

import com.example.BankingApplication.model.TestEntity;
import com.example.BankingApplication.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    TestService testService;

    @GetMapping
    public List<TestEntity> getAllTest() {
        return testService.getAllTest();
    }

    @GetMapping("/{id}")
    public TestEntity getBankaById(@PathVariable int id)
    {
        return testService.getById(id);
    }

    @PostMapping
    public void addBanka(@RequestBody TestEntity testEntity) {
        testService.addTest(testEntity);
    }

}
