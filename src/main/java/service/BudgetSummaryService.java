package com.example.demo.service;

import com.example.demo.model.BudgetSummary;

public interface BudgetSummaryService {

    BudgetSummary getSummaryByPlanId(Long budgetPlanId);
}
