package com.example.dao;

import com.example.entity.Transaction;
import java.util.List;

public interface TransactionDAO {
    void save(Transaction transaction);
    Transaction findById(Long transactionId);
    List<Transaction> findByAccount(Long accountId);
    List<Transaction> findAll();
}
