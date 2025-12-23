package com.example.demo.service.impl;

import com.example.demo.model.TransactionLog;
import com.example.demo.model.User;
import com.example.demo.repository.TransactionLogRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BudgetSummaryServiceImpl {

    private final TransactionLogRepository txRepo;
    private final UserRepository userRepo;

    public BudgetSummaryServiceImpl(TransactionLogRepository txRepo,
                                    UserRepository userRepo) {
        this.txRepo = txRepo;
        this.userRepo = userRepo;
    }

    public double calculateTotalExpense(Long userId) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<TransactionLog> logs = txRepo.findByUserId(user.getId());

        double total = 0;

        for (TransactionLog log : logs) {
            if ("EXPENSE".equalsIgnoreCase(log.getType())) {
                total += log.getAmount();
            }
        }

        return total;
    }
}
