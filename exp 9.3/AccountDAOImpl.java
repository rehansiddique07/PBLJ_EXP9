package com.example.dao;

import com.example.entity.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.List;

@Repository
public class AccountDAOImpl implements AccountDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Account findById(Long accountId) {
        return getCurrentSession().get(Account.class, accountId);
    }

    @Override
    public Account findByAccountNumber(String accountNumber) {
        Query<Account> query = getCurrentSession().createQuery(
            "FROM Account WHERE accountNumber = :accountNumber", Account.class);
        query.setParameter("accountNumber", accountNumber);
        return query.uniqueResult();
    }

    @Override
    public List<Account> findAll() {
        return getCurrentSession().createQuery("FROM Account", Account.class).list();
    }

    @Override
    public void save(Account account) {
        getCurrentSession().save(account);
    }

    @Override
    public void update(Account account) {
        getCurrentSession().update(account);
    }

    @Override
    public void delete(Long accountId) {
        Account account = findById(accountId);
        if (account != null) {
            getCurrentSession().delete(account);
        }
    }

    @Override
    public void updateBalance(Long accountId, BigDecimal newBalance) {
        Query<?> query = getCurrentSession().createQuery(
            "UPDATE Account SET balance = :balance WHERE accountId = :accountId");
        query.setParameter("balance", newBalance);
        query.setParameter("accountId", accountId);
        query.executeUpdate();
    }
}
