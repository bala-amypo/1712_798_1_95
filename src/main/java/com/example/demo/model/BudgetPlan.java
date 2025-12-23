package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class BudgetPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Integer month;
    private Integer year;
    private Double incomeTarget;
    private Double expenseLimit;

    public BudgetPlan() {}

    public Long getId() { return id; }
    public Long getUserId() { return userId; }
    public Integer getMonth() { return month; }
    public Integer getYear() { return year; }
    public Double getIncomeTarget() { return incomeTarget; }
    public Double getExpenseLimit() { return expenseLimit; }

    public void setId(Long id) { this.id = id; }
    public void setUserId(Long userId) { this.userId = userId; }
    public void setMonth(Integer month) { this.month = month; }
    public void setYear(Integer year) { this.year = year; }
    public void setIncomeTarget(Double incomeTarget) { this.incomeTarget = incomeTarget; }
    public void setExpenseLimit(Double expenseLimit) { this.expenseLimit = expenseLimit; }
}