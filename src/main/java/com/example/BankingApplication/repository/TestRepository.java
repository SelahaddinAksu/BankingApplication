package com.example.BankingApplication.repository;

import com.example.BankingApplication.model.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<TestEntity, Integer> {
}
