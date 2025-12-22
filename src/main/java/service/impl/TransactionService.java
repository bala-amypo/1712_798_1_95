package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.TransactionLog;
import com.example.demo.model.User;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.TransactionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository repo;
    private final UserRepository userRepo;

    public TransactionServiceImpl(TransactionRepository repo, UserRepository userRepo) {
        this.repo = repo;
        this.userRepo = userRepo;
    }

    @Override
    public TransactionLog addTransaction(Long userId, TransactionLog log) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        log.setUser(user);
        return repo.save(log);
    }

    @Override
    public List<TransactionLog> getUserTransactions(Long userId) {
        return repo.findByUserId(userId);
    }
}
