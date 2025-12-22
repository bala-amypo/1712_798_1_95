package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import com.example.demo.exception.BadRequestException;
import java.math.BigDecimal;

@Entity
@Table(name = "budget_plans")
public class BudgetPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    private Integer month;

    @NotNull
    private Integer year;

    @NotNull
    private BigDecimal incomeTarget;

    @NotNull
    private BigDecimal expenseLimit;

    public BudgetPlan() {}

    public BudgetPlan(Long id, User user, Integer month,
                      Integer year, BigDecimal incomeTarget, BigDecimal expenseLimit) {
        this.id = id;
        this.user = user;
        this.month = month;
        this.year = year;
        this.incomeTarget = incomeTarget;
        this.expenseLimit = expenseLimit;
    }

    public void validate() {
        if (user == null)
            throw new BadRequestException("User cannot be null");
        if (month == null || month < 1 || month > 12)
            throw new BadRequestException("Invalid month");
        if (incomeTarget == null || expenseLimit == null
            || incomeTarget.compareTo(BigDecimal.ZERO) < 0
            || expenseLimit.compareTo(BigDecimal.ZERO) < 0)
            throw new BadRequestException("Negative values not allowed");
    }

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public Integer getMonth() { return month; }
    public void setMonth(Integer month) { this.month = month; }
    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }
    public BigDecimal getIncomeTarget() { return incomeTarget; }
    public void setIncomeTarget(BigDecimal incomeTarget) { this.incomeTarget = incomeTarget; }
    public BigDecimal getExpenseLimit() { return expenseLimit; }
    public void setExpenseLimit(BigDecimal expenseLimit) { this.expenseLimit = expenseLimit; }
}
