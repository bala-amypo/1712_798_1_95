package com.example.demo.service.impl;

import com.example.demo.model.BudgetPlan;
import com.example.demo.model.BudgetSummary;
import com.example.demo.repository.BudgetPlanRepository;
import com.example.demo.repository.BudgetSummaryRepository;
import com.example.demo.service.BudgetSummaryService;
import org.springframework.stereotype.Service;

@Service
public class BudgetSummaryServiceImpl implements BudgetSummaryService {

    private final BudgetPlanRepository planRepo;
    private final BudgetSummaryRepository summaryRepo;

    public BudgetSummaryServiceImpl(
            BudgetPlanRepository planRepo,
            BudgetSummaryRepository summaryRepo) {
        this.planRepo = planRepo;
        this.summaryRepo = summaryRepo;
    }

    @Override
    public BudgetSummary generateSummary(Long budgetPlanId) {

        BudgetPlan plan = planRepo.findById(budgetPlanId)
                .orElseThrow(() -> new RuntimeException("Budget Plan not found"));

        BudgetSummary summary = new BudgetSummary();
        summary.setBudgetPlanId(plan.getId());
        summary.setTotalBudget(plan.getTotalAmount());
        summary.setRemainingAmount(plan.getTotalAmount());

        return summaryRepo.save(summary);
    }

    @Override
    public BudgetSummary getSummary(Long budgetPlanId) {
        return summaryRepo.findByBudgetPlanId(budgetPlanId)
                .orElseThrow(() -> new RuntimeException("Summary not found"));
    }
}
