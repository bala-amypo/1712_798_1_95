package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class TransactionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;
    private String type;
    private String category;

    @ManyToOne
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    public String getCategory() {
        return category;
    }
}
