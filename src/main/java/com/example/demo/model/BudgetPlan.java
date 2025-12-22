package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class BudgetPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int month;
    private int year;
    private double expenseLimit;

    @ManyToOne
    private User user;

    public BudgetPlan() {}

    public int getMonth() { return month; }
    public int getYear() { return year; }
    public double getExpenseLimit() { return expenseLimit; }
    public User getUser() { return user; }
}
