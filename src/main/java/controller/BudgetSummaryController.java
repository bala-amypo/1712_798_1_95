package com.example.demo.controller;

import com.example.demo.model.BudgetSummary;
import com.example.demo.service.BudgetSummaryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/budget-summary")
public class BudgetSummaryController {

    private final BudgetSummaryService budgetSummaryService;

    public BudgetSummaryController(BudgetSummaryService budgetSummaryService) {
        this.budgetSummaryService = budgetSummaryService;
    }

    @GetMapping("/{budgetPlanId}")
    public BudgetSummary getSummary(@PathVariable Long budgetPlanId) {
        return budgetSummaryService.getSummaryByPlanId(budgetPlanId);
    }
}
