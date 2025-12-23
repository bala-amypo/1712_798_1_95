package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "budget_summary")
public class BudgetSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long budgetPlanId;

    private Double totalBudget;
    private Double totalExpense;
    private Double remainingAmount;

    public BudgetSummary() {
    }

    public Long getId() {
        return id;
    }

    public Long getBudgetPlanId() {
        return budgetPlanId;
    }

    public void setBudgetPlanId(Long budgetPlanId) {
        this.budgetPlanId = budgetPlanId;
    }

    public Double getTotalBudget() {
        return totalBudget;
    }

    public void setTotalBudget(Double totalBudget) {
        this.totalBudget = totalBudget;
    }

    public Double getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(Double totalExpense) {
        this.totalExpense = totalExpense;
    }

    public Double getRemainingAmount() {
        return remainingAmount;
    }

    public void setRemainingAmount(Double remainingAmount) {
        this.remainingAmount = remainingAmount;
    }
}
