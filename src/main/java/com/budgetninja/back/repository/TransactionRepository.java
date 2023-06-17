package com.budgetninja.back.repository;

import com.budgetninja.back.model.TransactionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionModel, Long> {
    List<TransactionModel> findAllByBudgetUser_Id(Long userId);
}
