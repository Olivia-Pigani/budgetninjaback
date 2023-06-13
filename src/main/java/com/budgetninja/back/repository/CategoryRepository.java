package com.budgetninja.back.repository;

import com.budgetninja.back.model.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryModel, Long> {


}
