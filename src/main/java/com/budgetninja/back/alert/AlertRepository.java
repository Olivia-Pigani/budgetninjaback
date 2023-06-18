package com.budgetninja.back.alert;

import com.budgetninja.back.alert.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface AlertRepository extends JpaRepository<Alert, Long> {
    Alert findByUserId(Long userId);
}
