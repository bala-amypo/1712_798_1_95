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

    public BudgetSummaryServiceImpl(BudgetPlanRepository planRepo,
                                    BudgetSummaryRepository summaryRepo) {
        this.planRepo = planRepo;
        this.summaryRepo = summaryRepo;
    }

    @Override
    public BudgetSummary generateSummary(Long budgetPlanId) {
        BudgetPlan plan = planRepo.findById(budgetPlanId).orElseThrow();

        BudgetSummary summary = new BudgetSummary();
        summary.setBudgetPlan(plan);
        summary.setRemainingAmount(plan.getTotalAmount());

        return summaryRepo.save(summary);
    }

    @Override
    public BudgetSummary getSummary(Long budgetPlanId) {
        return summaryRepo.findAll()
                .stream()
                .filter(s -> s.getBudgetPlan().getId().equals(budgetPlanId))
                .findFirst()
                .orElse(null);
    }
}
