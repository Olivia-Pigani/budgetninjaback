package com.budgetninja.back.category;

import com.budgetninja.back.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByUserId(Long userId);

    Optional<Category> findByName(String name);
}
