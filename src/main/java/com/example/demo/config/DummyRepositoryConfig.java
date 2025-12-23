package com.example.demo.config;

import com.example.demo.model.BudgetPlan;
import com.example.demo.repository.BudgetPlanRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Configuration
public class DummyRepositoryConfig {

    @Bean
    public BudgetPlanRepository budgetPlanRepository() {
        return new BudgetPlanRepository() {

            @Override
            public Optional<BudgetPlan> findById(Long id) {
                return Optional.empty();
            }

            @Override
            public <S extends BudgetPlan> S save(S entity) {
                return entity;
            }

            // ---- REQUIRED METHODS (EMPTY IMPLEMENTATION) ----

            @Override public boolean existsById(Long id) { return false; }
            @Override public Iterable<BudgetPlan> findAll() { return java.util.List.of(); }
            @Override public long count() { return 0; }
            @Override public void deleteById(Long id) {}
            @Override public void delete(BudgetPlan entity) {}
            @Override public void deleteAll() {}
        };
    }
}
