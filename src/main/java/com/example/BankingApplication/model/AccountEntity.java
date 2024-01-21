package com.example.BankingApplication.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "account", schema = "public", catalog = "postgres")
public class AccountEntity {
    private int id;
    private String accountHolder;
    private int bankId;
    private Double totalAmount;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "account_holder", nullable = false, length = 255)
    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    @Basic
    @Column(name = "bank_id", nullable = false)
    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    @Basic
    @Column(name = "total_amount", nullable = false)
    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountEntity that = (AccountEntity) o;
        return id == that.id && bankId == that.bankId && Objects.equals(accountHolder, that.accountHolder) && Objects.equals(totalAmount, that.totalAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountHolder, bankId, totalAmount);
    }
}
