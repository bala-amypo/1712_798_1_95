package com.example.demo.controller;

import com.example.demo.model.TransactionLog;
import com.example.demo.repository.TransactionLogRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionLogRepository repo;

    public TransactionController(TransactionLogRepository repo) {
        this.repo = repo;
    }

    @PostMapping("/{userId}")
    public TransactionLog add(
            @PathVariable Long userId,
            @RequestBody TransactionLog log) {

        log.setUserId(userId);
        return repo.save(log);
    }

    @GetMapping("/user/{userId}")
    public List<TransactionLog> getByUser(@PathVariable Long userId) {
        return repo.findByUserId(userId);
    }
}