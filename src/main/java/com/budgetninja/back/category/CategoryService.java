package com.budgetninja.back.category;

import com.budgetninja.back.category.Category;
import com.budgetninja.back.category.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    public CategoryService(CategoryRepository categoryRepository){
       this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
    public Category findCategoryById(Long category_id){
        return categoryRepository.findById(category_id).orElseThrow(
                ()->new ResponseStatusException(HttpStatus.NOT_FOUND,"categories not found")
        );
    }

    public Category updateCategory(Category category, long category_id){
        Category existingCategory= categoryRepository.findById(category_id).orElse(null);
        if( existingCategory ==null){
            throw new RuntimeException("Category not found");

        }else{
            existingCategory.setName(category.getName());
            return categoryRepository.save(existingCategory);
        }

    }
    public void deleteCategoryById(Long category_id){
        categoryRepository.deleteById(category_id);
    }
    public Category save(Category category){
        return categoryRepository.save(category);
    }
}

