package com.example.demo.controller;

import com.example.demo.model.BudgetSummary;
import com.example.demo.service.BudgetSummaryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/budget-summary")
public class BudgetSummaryController {

    private final BudgetSummaryService service;

    public BudgetSummaryController(BudgetSummaryService service) {
        this.service = service;
    }

    @PostMapping("/{planId}")
    public BudgetSummary generate(@PathVariable Long planId) {
        return service.generateSummary(planId);
    }

    @GetMapping("/{planId}")
    public BudgetSummary get(@PathVariable Long planId) {
        return service.getSummary(planId);
    }
}
