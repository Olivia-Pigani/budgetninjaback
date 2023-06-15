package com.budgetninja.back.repository;

import com.budgetninja.back.model.NinjaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NinjaRepository extends JpaRepository<NinjaModel, Long> {
    NinjaModel findByUsername(String username);
}
