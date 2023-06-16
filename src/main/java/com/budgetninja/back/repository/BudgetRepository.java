package com.budgetninja.back.repository;

import com.budgetninja.back.model.BudgetModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetRepository extends JpaRepository<BudgetModel, Long> {
}
