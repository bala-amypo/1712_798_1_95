package com.example.demo.service;

import com.example.demo.model.TransactionLog;
import java.util.List;

public interface TransactionService {

    TransactionLog addTransaction(TransactionLog log, Long userId);

    List<TransactionLog> getUserTransactions(Long userId);
}
