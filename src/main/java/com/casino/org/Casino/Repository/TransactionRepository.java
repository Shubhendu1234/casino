package com.casino.org.Casino.Repository;

import com.casino.org.Casino.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,String> {
    List<Transaction> findAllByUserId(Integer id);
}
