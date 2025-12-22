package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.BudgetSummaryService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BudgetSummaryServiceImpl implements BudgetSummaryService {

    private final BudgetSummaryRepository summaryRepo;
    private final BudgetPlanRepository planRepo;
    private final TransactionLogRepository txRepo;

    public BudgetSummaryServiceImpl(
            BudgetSummaryRepository summaryRepo,
            BudgetPlanRepository planRepo,
            TransactionLogRepository txRepo) {
        this.summaryRepo = summaryRepo;
        this.planRepo = planRepo;
        this.txRepo = txRepo;
    }

    @Override
    public BudgetSummary generateSummary(Long planId) {
        BudgetPlan plan = planRepo.findById(planId)
                .orElseThrow(() -> new BadRequestException("Plan not found"));

        LocalDate start = LocalDate.of(plan.getYear(), plan.getMonth(), 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        List<TransactionLog> logs =
                txRepo.findByUserAndTransactionDateBetween(
                        plan.getUser(), start, end);

        double income = 0, expense = 0;

        for (TransactionLog log : logs) {
            if ("INCOME".equals(log.getCategory().getType())) income += log.getAmount();
            else expense += log.getAmount();
        }

        BudgetSummary summary = new BudgetSummary();
        summary.setBudgetPlan(plan);
        summary.setTotalIncome(income);
        summary.setTotalExpense(expense);
        summary.setStatus(expense <= plan.getExpenseLimit()
                ? BudgetSummary.STATUS_UNDER_LIMIT
                : BudgetSummary.STATUS_OVER_LIMIT);
        summary.setGeneratedAt(LocalDateTime.now());

        return summaryRepo.save(summary);
    }

    @Override
    public BudgetSummary getSummary(Long planId) {
        BudgetPlan plan = planRepo.findById(planId)
                .orElseThrow(() -> new BadRequestException("Plan not found"));

        return summaryRepo.findByBudgetPlan(plan)
                .orElseThrow(() -> new BadRequestException("Summary not found"));
    }
}
