package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.BudgetPlan;
import com.example.demo.model.BudgetSummary;
import com.example.demo.repository.BudgetPlanRepository;
import com.example.demo.repository.BudgetSummaryRepository;
import com.example.demo.repository.TransactionLogRepository;
import com.example.demo.service.BudgetSummaryService;
import org.springframework.stereotype.Service;

@Service
public class BudgetSummaryServiceImpl implements BudgetSummaryService {

    private final BudgetSummaryRepository budgetSummaryRepository;
    private final BudgetPlanRepository budgetPlanRepository;
    private final TransactionLogRepository transactionLogRepository;

    public BudgetSummaryServiceImpl(BudgetSummaryRepository budgetSummaryRepository,
                                    BudgetPlanRepository budgetPlanRepository,
                                    TransactionLogRepository transactionLogRepository) {
        this.budgetSummaryRepository = budgetSummaryRepository;
        this.budgetPlanRepository = budgetPlanRepository;
        this.transactionLogRepository = transactionLogRepository;
    }

    @Override
    public BudgetSummary getSummaryByPlanId(Long budgetPlanId) {

        BudgetPlan plan = budgetPlanRepository.findById(budgetPlanId)
                .orElseThrow(() -> new ResourceNotFoundException("Budget plan not found"));

        return budgetSummaryRepository.findByBudgetPlan(plan)
                .orElseThrow(() -> new ResourceNotFoundException("Summary not found"));
    }
}
