package com.example.demo.controller;

import com.example.demo.model.BudgetPlan;
import com.example.demo.service.BudgetPlanService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/budgets")
public class BudgetPlanController {

    private final BudgetPlanService budgetPlanService;

    public BudgetPlanController(BudgetPlanService budgetPlanService) {
        this.budgetPlanService = budgetPlanService;
    }

    @PostMapping
    public BudgetPlan createPlan(@RequestBody BudgetPlan plan) {

        Long userId = getCurrentUserId();
        return budgetPlanService.createBudgetPlan(userId, plan);
    }

    @GetMapping("/{month}/{year}")
    public BudgetPlan getPlan(
            @PathVariable Integer month,
            @PathVariable Integer year) {

        Long userId = getCurrentUserId();
        return budgetPlanService.getBudgetPlan(userId, month, year);
    }

    private Long getCurrentUserId() {
        Authentication auth =
                SecurityContextHolder.getContext().getAuthentication();

        return Long.parseLong(auth.getName());
    }
}
