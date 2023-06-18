package com.budgetninja.back.transaction;

import com.budgetninja.back.category.Category;
import com.budgetninja.back.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByBudgetUser_Id(Long userId);

    List<Transaction> findAllByCategory(Category category);
}
