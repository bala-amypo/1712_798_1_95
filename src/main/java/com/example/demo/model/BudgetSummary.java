package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class BudgetSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long budgetPlanId;
    private Double totalIncome;
    private Double totalExpense;
    private String status;

    private LocalDateTime generatedAt;

    @PrePersist
    public void onCreate() {
        generatedAt = LocalDateTime.now();
    }

    public BudgetSummary() {}

    public Long getId() { return id; }
    public Long getBudgetPlanId() { return budgetPlanId; }
    public Double getTotalIncome() { return totalIncome; }
    public Double getTotalExpense() { return totalExpense; }
    public String getStatus() { return status; }
    public LocalDateTime getGeneratedAt() { return generatedAt; }

    public void setId(Long id) { this.id = id; }
    public void setBudgetPlanId(Long budgetPlanId) { this.budgetPlanId = budgetPlanId; }
    public void setTotalIncome(Double totalIncome) { this.totalIncome = totalIncome; }
    public void setTotalExpense(Double totalExpense) { this.totalExpense = totalExpense; }
    public void setStatus(String status) { this.status = status; }
}