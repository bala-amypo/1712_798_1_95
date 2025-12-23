package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.BudgetPlan;

import java.util.Optional;

@Repository
public interface BudgetPlanRepository
        extends JpaRepository<BudgetPlan, Long> {

    Optional<BudgetPlan> findByUserIdAndMonthAndYear(
            Long userId,
            Integer month,
            Integer year
    );
}
