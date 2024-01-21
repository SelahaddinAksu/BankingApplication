package com.example.BankingApplication.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "TransactionType", schema = "public", catalog = "postgres")
public class TransactionTypeEntity {
    private int id;
    private String transactionType;

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
    @Column(name = "transaction_type", nullable = false, length = 50)
    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionTypeEntity that = (TransactionTypeEntity) o;
        return id == that.id && Objects.equals(transactionType, that.transactionType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, transactionType);
    }
}
