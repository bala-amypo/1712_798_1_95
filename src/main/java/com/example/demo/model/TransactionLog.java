package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class TransactionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double amount;
    private LocalDate transactionDate;

    @ManyToOne
    private User user;

    @ManyToOne
    private Category category;

    public TransactionLog() {}

    public double getAmount() { return amount; }
    public LocalDate getTransactionDate() { return transactionDate; }
    public User getUser() { return user; }
    public Category getCategory() { return category; }
}
