package com.example.BankingApplication.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Bank", schema = "public", catalog = "postgres")
public class BankEntity {
    private int id;
    private String bankName;

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
    @Column(name = "bank_name", nullable = true, length = 50)
    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankEntity that = (BankEntity) o;
        return id == that.id && Objects.equals(bankName, that.bankName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bankName);
    }
}
