package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.BudgetPlan;
import com.example.demo.model.User;
import com.example.demo.repository.BudgetPlanRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.BudgetPlanService;
import org.springframework.stereotype.Service;

@Service
public class BudgetPlanServiceImpl implements BudgetPlanService {

    private final BudgetPlanRepository planRepo;
    private final UserRepository userRepo;

    public BudgetPlanServiceImpl(BudgetPlanRepository planRepo, UserRepository userRepo) {
        this.planRepo = planRepo;
        this.userRepo = userRepo;
    }

    @Override
    public BudgetPlan createBudgetPlan(Long userId, BudgetPlan plan) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (planRepo.existsByUserAndMonthAndYear(user, plan.getMonth(), plan.getYear())) {
            throw new BadRequestException("Budget plan already exists for this month");
        }

        plan.setUser(user);
        return planRepo.save(plan);
    }

    @Override
    public BudgetPlan getBudgetPlan(Long userId, Integer month, Integer year) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return planRepo.findByUserAndMonthAndYear(user, month, year)
                .orElseThrow(() -> new ResourceNotFoundException("Budget plan not found"));
    }
}
