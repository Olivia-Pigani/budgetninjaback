package com.budgetninja.back.repository;

import com.budgetninja.back.model.SavingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingRepository extends JpaRepository<SavingModel, Long> {
}
