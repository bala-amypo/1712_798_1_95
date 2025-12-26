package com.example.demo.service.impl;

import com.example.demo.model.BudgetPlan;
import com.example.demo.model.BudgetSummary;
import com.example.demo.model.Category;
import com.example.demo.model.TransactionLog;
import com.example.demo.repository.BudgetPlanRepository;
import com.example.demo.repository.BudgetSummaryRepository;
import com.example.demo.repository.TransactionLogRepository;
import com.example.demo.service.BudgetSummaryService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

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
    public BudgetSummary generateSummary(Long budgetPlanId) {
        BudgetPlan plan = budgetPlanRepository.findById(budgetPlanId).orElseThrow();

        YearMonth ym = YearMonth.of(plan.getYear(), plan.getMonth());
        LocalDate start = ym.atDay(1);
        LocalDate end = ym.atEndOfMonth();

        List<TransactionLog> logs =
                transactionLogRepository.findByUserAndTransactionDateBetween(
                        plan.getUser(), start, end);

        double income = 0;
        double expense = 0;

        for (TransactionLog log : logs) {
            if (Category.TYPE_INCOME.equals(log.getCategory().getType())) {
                income += log.getAmount();
            } else {
                expense += log.getAmount();
            }
        }

        BudgetSummary summary = new BudgetSummary();
        summary.setBudgetPlan(plan);
        summary.setTotalIncome(income);
        summary.setTotalExpense(expense);
        summary.setStatus(
                expense > plan.getExpenseLimit()
                        ? BudgetSummary.STATUS_OVER_LIMIT
                        : BudgetSummary.STATUS_UNDER_LIMIT
        );

        summary.onCreate();
        return budgetSummaryRepository.save(summary);
    }

    @Override
    public BudgetSummary getSummary(Long budgetPlanId) {
        BudgetPlan plan = budgetPlanRepository.findById(budgetPlanId).orElseThrow();
        return budgetSummaryRepository.findByBudgetPlan(plan).orElseThrow();
    }
}