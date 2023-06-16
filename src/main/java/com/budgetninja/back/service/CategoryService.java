package com.budgetninja.back.service;

import com.budgetninja.back.model.CategoryModel;
import com.budgetninja.back.repository.CategoryRepository;
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

    public List<CategoryModel> getAllCategories() {
        return categoryRepository.findAll();
    }
    public CategoryModel findCategoryById(Long category_id){
        return categoryRepository.findById(category_id).orElseThrow(
                ()->new ResponseStatusException(HttpStatus.NOT_FOUND,"categories not found")
        );
    }

    public CategoryModel updateCategory(CategoryModel categoryModel, long category_id){
        CategoryModel existingCategory= categoryRepository.findById(category_id).orElse(null);
        if( existingCategory ==null){
            throw new RuntimeException("Category not found");

        }else{
            existingCategory.setCategorieName(categoryModel.getCategorieName());
            return categoryRepository.save(existingCategory);
        }

    }
    public void deleteCategoryById(Long category_id){
        categoryRepository.deleteById(category_id);
    }
    public CategoryModel save(CategoryModel categoryModel){
        return categoryRepository.save(categoryModel);
    }
}

