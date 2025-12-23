package com.example.demo.service.impl;

import com.example.demo.model.BudgetSummary;
import com.example.demo.service.BudgetSummaryService;
import org.springframework.stereotype.Service;

@Service
public class BudgetSummaryServiceImpl implements BudgetSummaryService {

    @Override
    public BudgetSummary generateSummary(Long budgetPlanId) {
        // Dummy implementation to avoid startup failure
        BudgetSummary summary = new BudgetSummary();
        summary.setId(1L);
        summary.setTotalIncome(0.0);
        summary.setTotalExpense(0.0);
        summary.setSavings(0.0);
        return summary;
    }

    @Override
    public BudgetSummary getSummary(Long budgetPlanId) {
        // Dummy implementation
        BudgetSummary summary = new BudgetSummary();
        summary.setId(1L);
        summary.setTotalIncome(0.0);
        summary.setTotalExpense(0.0);
        summary.setSavings(0.0);
        return summary;
    }
}
