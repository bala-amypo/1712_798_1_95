package com.example.demo.service.impl;

import com.example.demo.model.TransactionLog;
import com.example.demo.model.User;
import com.example.demo.repository.TransactionLogRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.TransactionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionLogRepository transactionRepo;
    private final UserRepository userRepo;

    public TransactionServiceImpl(TransactionLogRepository transactionRepo,
                                  UserRepository userRepo) {
        this.transactionRepo = transactionRepo;
        this.userRepo = userRepo;
    }

    @Override
    public TransactionLog addTransaction(TransactionLog log, Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        log.setUser(user);
        return transactionRepo.save(log);
    }

    @Override
    public List<TransactionLog> getUserTransactions(Long userId) {
        return transactionRepo.findByUserId(userId);
    }
}
