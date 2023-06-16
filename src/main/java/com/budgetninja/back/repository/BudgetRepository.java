package com.budgetninja.back.repository;

import com.budgetninja.back.model.BudgetModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BudgetRepository extends JpaRepository<BudgetModel, Long> {
    Optional<BudgetModel> findByUser_Id(Long userId);
}
