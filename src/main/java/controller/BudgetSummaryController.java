package com.example.demo.controller;

import com.example.demo.model.BudgetSummary;
import com.example.demo.service.BudgetSummaryService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/summary")
public class BudgetSummaryController {

    private final BudgetSummaryService budgetSummaryService;

    public BudgetSummaryController(BudgetSummaryService budgetSummaryService) {
        this.budgetSummaryService = budgetSummaryService;
    }

    @PostMapping("/generate/{budgetPlanId}")
    public BudgetSummary generate(@PathVariable Long budgetPlanId) {
        Long userId = getCurrentUserId();
        return budgetSummaryService.generateSummary(userId, budgetPlanId);
    }

    @GetMapping("/{budgetPlanId}")
    public BudgetSummary get(@PathVariable Long budgetPlanId) {
        Long userId = getCurrentUserId();
        return budgetSummaryService.getSummary(userId, budgetPlanId);
    }

    private Long getCurrentUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return Long.parseLong(auth.getName());
    }
}
