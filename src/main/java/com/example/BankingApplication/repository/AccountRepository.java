package com.example.BankingApplication.repository;

import com.example.BankingApplication.model.AccountEntity;
import com.example.BankingApplication.model.BankEntity;
import com.example.BankingApplication.model.TransactionLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {
    AccountEntity findById(AccountEntity id);
}
