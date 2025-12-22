package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
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

    public BudgetSummaryServiceImpl(BudgetPlanRepository planRepo,
                                    BudgetSummaryRepository summaryRepo) {
        this.planRepo = planRepo;
        this.summaryRepo = summaryRepo;
    }

    @Override
    public BudgetSummary generateSummary(Long planId) {
        BudgetPlan plan = planRepo.findById(planId)
                .orElseThrow(() -> new ResourceNotFoundException("Budget plan not found"));

        BudgetSummary summary = summaryRepo.findByBudgetPlan(plan)
                .orElse(new BudgetSummary());

        summary.setBudgetPlan(plan);
        summary.setTotalIncome(plan.getTotalIncome());
        summary.setTotalExpense(plan.getTotalExpense());
        summary.setSavings(plan.getTotalIncome() - plan.getTotalExpense());

        return summaryRepo.save(summary);
    }

    @Override
    public BudgetSummary getSummary(Long planId) {
        return summaryRepo.findByBudgetPlanId(planId)
                .orElseThrow(() -> new ResourceNotFoundException("Summary not found"));
    }
}
