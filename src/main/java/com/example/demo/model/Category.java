package com.example.demo.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {
    
    public static final String TYPE_INCOME = "INCOME";
    public static final String TYPE_EXPENSE = "EXPENSE";
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String type;
    
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<TransactionLog> transactions = new ArrayList<>();
    
    public Category() {}
    
    public Category(Long id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }
    
    public void validateType() {
        if (!TYPE_INCOME.equals(type) && !TYPE_EXPENSE.equals(type)) {
            throw new IllegalArgumentException("Type must be either INCOME or EXPENSE");
        }
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    
    public List<TransactionLog> getTransactions() { return transactions; }
    public void setTransactions(List<TransactionLog> transactions) { this.transactions = transactions; }
}