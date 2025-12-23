package com.example.demo.repository;

import com.example.demo.model.BudgetPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BudgetPlanRepository extends JpaRepository<BudgetPlan, Long> {

    Optional<BudgetPlan> findByUserIdAndMonthAndYear(
            Long userId,
            Integer month,
            Integer year
    );
}