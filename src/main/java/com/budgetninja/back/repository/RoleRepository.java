package com.budgetninja.back.repository;

import com.budgetninja.back.model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository  extends JpaRepository<RoleModel, Long> {
    RoleModel findByRole(String role);
}
