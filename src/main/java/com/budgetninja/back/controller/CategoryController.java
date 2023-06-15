package com.budgetninja.back.controller;

import com.budgetninja.back.model.CategoryModel;
import com.budgetninja.back.model.UserModel;
import com.budgetninja.back.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping("/categorieList")
public class CategoryController {
private final CategoryService categoryService;
public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
@GetMapping("")
    public List<CategoryModel> getAllCategories(){
        return categoryService.getAllCategories();
}
@GetMapping("/{id}")
    public CategoryModel getCategoryById(@PathVariable Long id){
        return categoryService.findCategoryById(id);
}
@PostMapping("")
    public CategoryModel createCategory(@RequestBody CategoryModel categoryModel){
        return categoryService.save(categoryModel);
}
@PostMapping("{id}")
    public CategoryModel updateCategory(@RequestBody CategoryModel categoryModel, @PathVariable long id){
        return categoryService.updateCategory(categoryModel,id);
}

@DeleteMapping("{id}")
public void deleteCategory(@PathVariable Long id){
        categoryService.deleteCategoryById(id);
}

}
