package com.example.BankingApplication.repository;

import com.example.BankingApplication.model.AccountEntity;
import com.example.BankingApplication.model.TransactionLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionLogRepository extends JpaRepository<TransactionLogEntity, Integer> {
    TransactionLogEntity findByAccountId(AccountEntity id);
    List<TransactionLogEntity> findTransactionLogByAccountId(AccountEntity id);
}


