package com.example.dao;

import com.example.entity.Account;
import java.math.BigDecimal;
import java.util.List;

public interface AccountDAO {
    Account findById(Long accountId);
    Account findByAccountNumber(String accountNumber);
    List<Account> findAll();
    void save(Account account);
    void update(Account account);
    void delete(Long accountId);
    void updateBalance(Long accountId, BigDecimal newBalance);
}
