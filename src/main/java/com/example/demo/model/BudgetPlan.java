package com.example.demo.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "budget_plans")
public class BudgetPlan {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @Column(nullable = false)
    private Integer month;
    
    @Column(nullable = false)
    private Integer year;
    
    @Column(nullable = false)
    private Double incomeTarget;
    
    @Column(nullable = false)
    private Double expenseLimit;
    
    @OneToOne(mappedBy = "budgetPlan", cascade = CascadeType.ALL)
    private BudgetSummary budgetSummary;
    
    public BudgetPlan() {}
    
    public BudgetPlan(Long id, User user, Integer month, Integer year, 
                     Double incomeTarget, Double expenseLimit) {
        this.id = id;
        this.user = user;
        this.month = month;
        this.year = year;
        this.incomeTarget = incomeTarget;
        this.expenseLimit = expenseLimit;
    }
    
    public void validate() {
        if (month == null || month < 1 || month > 12) {
            throw new IllegalArgumentException("Month must be between 1 and 12");
        }
        if (incomeTarget == null || incomeTarget < 0) {
            throw new IllegalArgumentException("Income target must be >= 0");
        }
        if (expenseLimit == null || expenseLimit < 0) {
            throw new IllegalArgumentException("Expense limit must be >= 0");
        }
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BudgetPlan that = (BudgetPlan) o;
        return Objects.equals(user, that.user) && 
               Objects.equals(month, that.month) && 
               Objects.equals(year, that.year);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(user, month, year);
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    
    public Integer getMonth() { return month; }
    public void setMonth(Integer month) { this.month = month; }
    
    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }
    
    public Double getIncomeTarget() { return incomeTarget; }
    public void setIncomeTarget(Double incomeTarget) { this.incomeTarget = incomeTarget; }
    
    public Double getExpenseLimit() { return expenseLimit; }
    public void setExpenseLimit(Double expenseLimit) { this.expenseLimit = expenseLimit; }
    
    public BudgetSummary getBudgetSummary() { return budgetSummary; }
    public void setBudgetSummary(BudgetSummary budgetSummary) { this.budgetSummary = budgetSummary; }
}