package com.example.service;

import com.example.entity.Account;
import com.example.entity.Transaction;
import java.math.BigDecimal;
import java.util.List;

public interface BankingService {
    // Account operations
    Account createAccount(Account account);
    Account getAccount(Long accountId);
    Account getAccountByNumber(String accountNumber);
    List<Account> getAllAccounts();
    
    // Transaction operations
    Transaction transferMoney(String fromAccountNumber, String toAccountNumber, 
                            BigDecimal amount, String description);
    void deposit(String accountNumber, BigDecimal amount);
    void withdraw(String accountNumber, BigDecimal amount);
    
    // Transaction history
    List<Transaction> getTransactionHistory(Long accountId);
    List<Transaction> getAllTransactions();
}
