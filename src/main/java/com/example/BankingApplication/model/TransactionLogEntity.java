package com.example.BankingApplication.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "TransactionLog", schema = "public", catalog = "postgres")
public class TransactionLogEntity {
    private int id;
    private int accountId;
    private Date transactionDate;
    private Double transactionAmount;
    private Double totalAmount;
    private String transactionType;
    private int bankId;

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
    @Column(name = "account_id", nullable = false)
    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    @Basic
    @Column(name = "transaction_date", nullable = false)
    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    @Basic
    @Column(name = "transaction_amount", nullable = false, precision = 0)
    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    @Basic
    @Column(name = "total_amount", nullable = false, precision = 0)
    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Basic
    @Column(name = "transaction_type", nullable = false, length = -1)
    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    @Basic
    @Column(name = "bank_id", nullable = false)
    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionLogEntity that = (TransactionLogEntity) o;
        return id == that.id && accountId == that.accountId && Double.compare(transactionAmount, that.transactionAmount) == 0 && Double.compare(totalAmount, that.totalAmount) == 0 && bankId == that.bankId && Objects.equals(transactionDate, that.transactionDate) && Objects.equals(transactionType, that.transactionType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountId, transactionDate, transactionAmount, totalAmount, transactionType, bankId);
    }
}
