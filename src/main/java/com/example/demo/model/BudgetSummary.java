package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class BudgetSummary {

    public static final String STATUS_UNDER_LIMIT = "UNDER_LIMIT";
    public static final String STATUS_OVER_LIMIT = "OVER_LIMIT";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private BudgetPlan budgetPlan;

    private double totalIncome;
    private double totalExpense;
    private String status;
    private LocalDateTime generatedAt;

    public BudgetSummary() {}

    public void setBudgetPlan(BudgetPlan plan) { this.budgetPlan = plan; }
    public void setTotalIncome(double income) { this.totalIncome = income; }
    public void setTotalExpense(double expense) { this.totalExpense = expense; }
    public void setStatus(String status) { this.status = status; }
    public void setGeneratedAt(LocalDateTime time) { this.generatedAt = time; }
}
