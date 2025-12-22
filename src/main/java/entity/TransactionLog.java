package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import com.example.demo.exception.BadRequestException;

@Entity
@Table(name = "transaction_logs")
public class TransactionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Category category;

    private Double amount;
    private String description;
    private LocalDate transactionDate;

    public TransactionLog() {}

    public TransactionLog(Long id, User user, Category category,
                          Double amount, String description, LocalDate transactionDate) {
        this.id = id;
        this.user = user;
        this.category = category;
        this.amount = amount;
        this.description = description;
        this.transactionDate = transactionDate;
    }

    public void validate() {
        if (amount <= 0)
            throw new BadRequestException("Amount must be positive");
        if (transactionDate.isAfter(LocalDate.now()))
            throw new BadRequestException("Future date not allowed");
    }

    // getters and setters
}
