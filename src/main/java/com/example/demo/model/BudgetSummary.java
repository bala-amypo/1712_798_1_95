package com.example.demo.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "budget_summaries")
public class BudgetSummary {

    public static final String STATUS_UNDER_LIMIT = "UNDER_LIMIT";
    public static final String STATUS_OVER_LIMIT = "OVER_LIMIT";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    private BudgetPlan budgetPlan;

    private Double totalIncome;

    private Double totalExpense;

    private String status;

    private LocalDateTime generatedAt;

    public BudgetSummary() {}

    public BudgetSummary(Long id, BudgetPlan budgetPlan,
                         Double totalIncome, Double totalExpense,
                         String status, LocalDateTime generatedAt) {
        this.id = id;
        this.budgetPlan = budgetPlan;
        this.totalIncome = totalIncome;
        this.totalExpense = totalExpense;
        this.status = status;
        this.generatedAt = generatedAt;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public BudgetPlan getBudgetPlan() { return budgetPlan; }

    public void setBudgetPlan(BudgetPlan budgetPlan) { this.budgetPlan = budgetPlan; }

    public Double getTotalIncome() { return totalIncome; }

    public void setTotalIncome(Double totalIncome) { this.totalIncome = totalIncome; }

    public Double getTotalExpense() { return totalExpense; }

    public void setTotalExpense(Double totalExpense) { this.totalExpense = totalExpense; }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getGeneratedAt() { return generatedAt; }

    public void setGeneratedAt(LocalDateTime generatedAt) {
        this.generatedAt = generatedAt;
    }

    @PrePersist
    public void onCreate() {
        this.generatedAt = LocalDateTime.now();
    }
}
























// package com.example.demo.model;

// import java.math.BigDecimal;
// import java.time.LocalDate;

// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.EnumType;
// import jakarta.persistence.Enumerated;
// import jakarta.persistence.FetchType;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.OneToOne;
// import jakarta.persistence.PrePersist;

// import jakarta.validation.constraints.NotNull;
// import jakarta.validation.constraints.PositiveOrZero;

// import lombok.AllArgsConstructor;
// import lombok.Data;
// import lombok.NoArgsConstructor;

// @Entity
// @Data
// @NoArgsConstructor
// @AllArgsConstructor
// public class BudgetSummary {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     // 🔗 One Summary → One BudgetPlan
//     @NotNull
//     @OneToOne(fetch = FetchType.LAZY)
//     @JoinColumn(name = "budget_plan_id", nullable = false, unique = true)
//     private BudgetPlan budgetPlan;

//     @NotNull
//     @PositiveOrZero
//     @Column(nullable = false, precision = 12, scale = 2)
//     private BigDecimal totalIncome;

//     @NotNull
//     @PositiveOrZero
//     @Column(nullable = false, precision = 12, scale = 2)
//     private BigDecimal totalExpense;

//     @NotNull
//     @Enumerated(EnumType.STRING)
//     @Column(nullable = false)
//     private BudgetStatus status;   // AUTO-CALCULATED

//     @NotNull
//     @Column(nullable = false)
//     private LocalDate generatedAt; // AUTO-SET

//     @PrePersist
//     protected void onCreate() {
//         // Default values (safety)
//         if (this.totalIncome == null) {
//             this.totalIncome = BigDecimal.ZERO;
//         }
//         if (this.totalExpense == null) {
//             this.totalExpense = BigDecimal.ZERO;
//         }

//         // Auto-set generation date
//         this.generatedAt = LocalDate.now();

//         // Auto-calculate status
//         if (this.totalExpense.compareTo(this.totalIncome) <= 0) {
//             this.status = BudgetStatus.UNDER_LIMIT;
//         } else {
//             this.status = BudgetStatus.OVER_LIMIT;
//         }
//     }
// }
