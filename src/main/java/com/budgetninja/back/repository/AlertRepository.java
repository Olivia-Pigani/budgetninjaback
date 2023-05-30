package com.budgetninja.back.repository;

import com.budgetninja.back.model.AlertModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface AlertRepository extends JpaRepository<AlertModel, Long> {








}
