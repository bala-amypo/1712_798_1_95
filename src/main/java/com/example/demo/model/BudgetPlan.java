package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class BudgetPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer month;
    private Integer year;
    private Double expenseLimit;

    @ManyToOne
    private User user;

    public BudgetPlan() {}

    public Integer getMonth() { return month; }
    public Integer getYear() { return year; }
    public Double getExpenseLimit() { return expenseLimit; }
    public User getUser() { return user; }

    public void setUser(User user) {
        this.user = user;
    }
}
