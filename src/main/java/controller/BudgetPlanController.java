package com.example.demo.controller;

import com.example.demo.model.BudgetPlan;
import com.example.demo.repository.BudgetPlanRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/budgets")
public class BudgetPlanController {

    private final BudgetPlanRepository repo;

    public BudgetPlanController(BudgetPlanRepository repo) {
        this.repo = repo;
    }

    @PostMapping("/{userId}")
    public BudgetPlan create(
            @PathVariable Long userId,
            @RequestBody BudgetPlan plan) {

        plan.setUserId(userId);
        return repo.save(plan);
    }

    @GetMapping("/{userId}/{month}/{year}")
    public BudgetPlan get(
            @PathVariable Long userId,
            @PathVariable Integer month,
            @PathVariable Integer year) {

        return repo.findByUserIdAndMonthAndYear(userId, month, year)
                .orElse(null);
    }
}