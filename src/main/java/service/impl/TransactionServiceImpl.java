package com.example.demo.service.impl;

import com.example.demo.model.TransactionLog;
import com.example.demo.model.User;
import com.example.demo.repository.TransactionLogRepository;
import com.example.demo.service.TransactionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionLogRepository repository;

    public TransactionServiceImpl(TransactionLogRepository repository) {
        this.repository = repository;
    }

    @Override
    public TransactionLog addTransaction(TransactionLog log, User user) {
        log.setUser(user);
        return repository.save(log);
    }

    @Override
    public List<TransactionLog> getTransactions(User user) {
        return repository.findByUser(user);
    }
}
