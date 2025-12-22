package com.example.demo.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "budget_summaries")
public class BudgetSummary {

    public enum SummaryStatus { UNDER_LIMIT, OVER_LIMIT }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "budget_plan_id", nullable = false)
    private BudgetPlan budgetPlan;

    private BigDecimal totalIncome;
    private BigDecimal totalExpense;

    @Enumerated(EnumType.STRING)
    private SummaryStatus status;

    private LocalDateTime generatedAt;

    public BudgetSummary() {}

    public BudgetSummary(Long id, BudgetPlan budgetPlan,
                         BigDecimal totalIncome, BigDecimal totalExpense,
                         SummaryStatus status, LocalDateTime generatedAt) {
        this.id = id;
        this.budgetPlan = budgetPlan;
        this.totalIncome = totalIncome;
        this.totalExpense = totalExpense;
        this.status = status;
        this.generatedAt = generatedAt;
    }

    @PrePersist
    public void onCreate() {
        this.generatedAt = LocalDateTime.now();
    }

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public BudgetPlan getBudgetPlan() { return budgetPlan; }
    public void setBudgetPlan(BudgetPlan budgetPlan) { this.budgetPlan = budgetPlan; }
    public BigDecimal getTotalIncome() { return totalIncome; }
    public void setTotalIncome(BigDecimal totalIncome) { this.totalIncome = totalIncome; }
    public BigDecimal getTotalExpense() { return totalExpense; }
    public void setTotalExpense(BigDecimal totalExpense) { this.totalExpense = totalExpense; }
    public SummaryStatus getStatus() { return status; }
    public void setStatus(SummaryStatus status) { this.status = status; }
    public LocalDateTime getGeneratedAt() { return generatedAt; }
    public void setGeneratedAt(LocalDateTime generatedAt) { this.generatedAt = generatedAt; }
}
