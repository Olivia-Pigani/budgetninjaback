package com.budgetninja.back.project;

import com.budgetninja.back.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Project findBySavingBudgetUser_Id(Long userId);

    void deleteAllBySavingBudget_Id(Long id);

}
