package com.example.demo.repository;

import com.example.demo.model.BudgetPlan;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetPlanRepository extends JpaRepository<BudgetPlan, Long> {

    boolean existsByUserAndMonthAndYear(User user, int month, int year);
}
