package com.example.dao;

import com.example.entity.Transaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class TransactionDAOImpl implements TransactionDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void save(Transaction transaction) {
        getCurrentSession().save(transaction);
    }

    @Override
    public Transaction findById(Long transactionId) {
        return getCurrentSession().get(Transaction.class, transactionId);
    }

    @Override
    public List<Transaction> findByAccount(Long accountId) {
        Query<Transaction> query = getCurrentSession().createQuery(
            "FROM Transaction WHERE fromAccount.accountId = :accountId OR toAccount.accountId = :accountId " +
            "ORDER BY transactionDate DESC", Transaction.class);
        query.setParameter("accountId", accountId);
        return query.list();
    }

    @Override
    public List<Transaction> findAll() {
        return getCurrentSession().createQuery("FROM Transaction ORDER BY transactionDate DESC", Transaction.class).list();
    }
}
