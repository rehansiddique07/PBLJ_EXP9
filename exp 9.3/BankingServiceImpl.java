package com.example.service;

import com.example.dao.AccountDAO;
import com.example.dao.TransactionDAO;
import com.example.entity.Account;
import com.example.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class BankingServiceImpl implements BankingService {

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private TransactionDAO transactionDAO;

    @Override
    public Account createAccount(Account account) {
        accountDAO.save(account);
        return account;
    }

    @Override
    @Transactional(readOnly = true)
    public Account getAccount(Long accountId) {
        return accountDAO.findById(accountId);
    }

    @Override
    @Transactional(readOnly = true)
    public Account getAccountByNumber(String accountNumber) {
        return accountDAO.findByAccountNumber(accountNumber);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Account> getAllAccounts() {
        return accountDAO.findAll();
    }

    @Override
    @Transactional
    public Transaction transferMoney(String fromAccountNumber, String toAccountNumber, 
                                   BigDecimal amount, String description) {
        
        // Validate amount
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Transfer amount must be positive");
        }

        // Find accounts
        Account fromAccount = accountDAO.findByAccountNumber(fromAccountNumber);
        Account toAccount = accountDAO.findByAccountNumber(toAccountNumber);

        if (fromAccount == null) {
            throw new RuntimeException("From account not found: " + fromAccountNumber);
        }
        if (toAccount == null) {
            throw new RuntimeException("To account not found: " + toAccountNumber);
        }
        if (fromAccount.equals(toAccount)) {
            throw new RuntimeException("Cannot transfer to the same account");
        }

        // Check sufficient balance
        if (fromAccount.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient balance in account: " + fromAccountNumber);
        }

        try {
            // Create transaction record
            Transaction transaction = new Transaction(fromAccount, toAccount, amount, description);
            transaction.setStatus("PENDING");
            
            // Perform transfer
            fromAccount.withdraw(amount);
            toAccount.deposit(amount);
            
            // Update accounts
            accountDAO.update(fromAccount);
            accountDAO.update(toAccount);
            
            // Mark transaction as successful
            transaction.setStatus("SUCCESS");
            transactionDAO.save(transaction);
            
            System.out.println("✅ Transfer successful: $" + amount + " from " + 
                             fromAccountNumber + " to " + toAccountNumber);
            
            return transaction;
            
        } catch (Exception e) {
            // Transaction will be automatically rolled back due to @Transactional
            System.err.println("❌ Transfer failed: " + e.getMessage());
            throw new RuntimeException("Transfer failed: " + e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public void deposit(String accountNumber, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Deposit amount must be positive");
        }

        Account account = accountDAO.findByAccountNumber(accountNumber);
        if (account == null) {
            throw new RuntimeException("Account not found: " + accountNumber);
        }

        account.deposit(amount);
        accountDAO.update(account);
        
        // Record transaction
        Transaction transaction = new Transaction();
        transaction.setToAccount(account);
        transaction.setAmount(amount);
        transaction.setTransactionType("DEPOSIT");
        transaction.setStatus("SUCCESS");
        transaction.setDescription("Cash deposit");
        transactionDAO.save(transaction);
        
        System.out.println("✅ Deposit successful: $" + amount + " to " + accountNumber);
    }

    @Override
    @Transactional
    public void withdraw(String accountNumber, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Withdrawal amount must be positive");
        }

        Account account = accountDAO.findByAccountNumber(accountNumber);
        if (account == null) {
            throw new RuntimeException("Account not found: " + accountNumber);
        }

        account.withdraw(amount);
        accountDAO.update(account);
        
        // Record transaction
        Transaction transaction = new Transaction();
        transaction.setFromAccount(account);
        transaction.setAmount(amount);
        transaction.setTransactionType("WITHDRAWAL");
        transaction.setStatus("SUCCESS");
        transaction.setDescription("Cash withdrawal");
        transactionDAO.save(transaction);
        
        System.out.println("✅ Withdrawal successful: $" + amount + " from " + accountNumber);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Transaction> getTransactionHistory(Long accountId) {
        return transactionDAO.findByAccount(accountId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Transaction> getAllTransactions() {
        return transactionDAO.findAll();
    }
}
