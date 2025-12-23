package com.example.demo.controller;

import com.example.demo.model.BudgetSummary;
import com.example.demo.repository.BudgetSummaryRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/summary")
public class BudgetSummaryController {

    private final BudgetSummaryRepository repo;

    public BudgetSummaryController(BudgetSummaryRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public BudgetSummary generate(@RequestBody BudgetSummary summary) {
        return repo.save(summary);
    }

    @GetMapping("/{budgetPlanId}")
    public BudgetSummary get(@PathVariable Long budgetPlanId) {
        return repo.findByBudgetPlanId(budgetPlanId).orElse(null);
    }
}