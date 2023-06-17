package com.budgetninja.back.repository;

import com.budgetninja.back.model.ProjectModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectModel, Long> {
    ProjectModel findByBudgetUser_Id(Long userId);
}
