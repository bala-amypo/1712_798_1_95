package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.*;
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
        BudgetPlan budgetPlan = budgetPlanRepository.findById(budgetPlanId)
                .orElseThrow(() -> new BadRequestException("Budget plan not found"));
        
        // Calculate date range for the month
        YearMonth yearMonth = YearMonth.of(budgetPlan.getYear(), budgetPlan.getMonth());
        LocalDate startDate = yearMonth.atDay(1);
        LocalDate endDate = yearMonth.atEndOfMonth();
        
        // Get transactions for the user in the date range
        List<TransactionLog> transactions = transactionLogRepository
                .findByUserAndTransactionDateBetween(budgetPlan.getUser(), startDate, endDate);
        
        // Calculate totals
        double totalIncome = 0.0;
        double totalExpense = 0.0;
        
        for (TransactionLog transaction : transactions) {
            if (Category.TYPE_INCOME.equals(transaction.getCategory().getType())) {
                totalIncome += transaction.getAmount();
            } else if (Category.TYPE_EXPENSE.equals(transaction.getCategory().getType())) {
                totalExpense += transaction.getAmount();
            }
        }
        
        // Determine status
        String status = totalExpense <= budgetPlan.getExpenseLimit() 
                ? BudgetSummary.STATUS_UNDER_LIMIT 
                : BudgetSummary.STATUS_OVER_LIMIT;
        
        // Create and save summary
        BudgetSummary summary = new BudgetSummary();
        summary.setBudgetPlan(budgetPlan);
        summary.setTotalIncome(totalIncome);
        summary.setTotalExpense(totalExpense);
        summary.setStatus(status);
        
        return budgetSummaryRepository.save(summary);
    }
    
    @Override
    public BudgetSummary getSummary(Long budgetPlanId) {
        BudgetPlan budgetPlan = budgetPlanRepository.findById(budgetPlanId)
                .orElseThrow(() -> new BadRequestException("Budget plan not found"));
        
        return budgetSummaryRepository.findByBudgetPlan(budgetPlan)
                .orElseThrow(() -> new BadRequestException("Summary not found"));
    }
}