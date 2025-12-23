package com.example.demo.config;

import com.example.demo.model.BudgetPlan;
import com.example.demo.repository.BudgetPlanRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;

@Configuration
public class DummyRepositoryConfig {

    @Bean
    public BudgetPlanRepository budgetPlanRepository() {
        return new BudgetPlanRepository() {

            // ===== Custom Query Method =====
            @Override
            public Optional<BudgetPlan> findByUserIdAndMonthAndYear(
                    Long userId, Integer month, Integer year) {
                return Optional.empty();
            }

            // ===== CRUD Methods =====
            @Override
            public <S extends BudgetPlan> S save(S entity) {
                return entity;
            }

            @Override
            public Optional<BudgetPlan> findById(Long id) {
                return Optional.empty();
            }

            @Override
            public List<BudgetPlan> findAll() {
                return List.of();
            }

            @Override
            public boolean existsById(Long id) {
                return false;
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public void deleteById(Long id) {
            }

            @Override
            public void delete(BudgetPlan entity) {
            }

            @Override
            public void deleteAll() {
            }
        };
    }
}
