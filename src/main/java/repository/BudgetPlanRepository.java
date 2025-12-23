package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.BudgetPlan;

@Repository
public interface BudgetPlanRepository
        extends JpaRepository<BudgetPlan, Long> {

    // Optional custom query methods (if needed later)
    // List<BudgetPlan> findByName(String name);
}
