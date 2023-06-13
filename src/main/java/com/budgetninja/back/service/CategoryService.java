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

    public List<CategoryModel> findAll() {
        return categoryRepository.findAll();
    }
    public CategoryModel findById(Long id){
        return categoryRepository.findById(id).orElseThrow(
                ()->new ResponseStatusException(HttpStatus.NOT_FOUND,"categories not found")
        );
    }
    public CategoryModel update(CategoryModel categoryModel, long id){
        CategoryModel existingCategory= categoryRepository.findById(id).orElse(null);
        if( existingCategory ==null){
            throw new RuntimeException("Category not found");

        }else{
            existingCategory.setCategorieName(categoryModel.getCategorieName());
            return categoryRepository.save(existingCategory);
        }

    }
    public void deleteById(Long id){
        categoryRepository.deleteById(id);
    }
    public CategoryModel save(CategoryModel categoryModel){
        return categoryRepository.save(categoryModel);
    }
}

