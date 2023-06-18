package com.budgetninja.back.saving;

import com.budgetninja.back.saving.Saving;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingRepository extends JpaRepository<Saving, Long> {
    Saving findByBudgetUser_Id(Long userId);

    void deleteAllByBudget_Id(Long id);
}
