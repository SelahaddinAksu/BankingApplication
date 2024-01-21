package com.example.BankingApplication.repository;

import com.example.BankingApplication.model.BankEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<BankEntity, Integer> {
}
