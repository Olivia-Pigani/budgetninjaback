package com.budgetninja.back.controller;

import com.budgetninja.back.model.CategoryModel;
import com.budgetninja.back.model.NinjaModel;
import com.budgetninja.back.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping("/categories")
public class CategoryController {
private final CategoryService categoryService;
public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
@GetMapping("")
    public List<CategoryModel> getAllCategories(){
        return categoryService.getAllCategories();
}
@GetMapping("/{category_id}")
    public CategoryModel getCategoryById(@PathVariable Long category_id){
        return categoryService.findCategoryById(category_id);
}
@PostMapping("")
    public CategoryModel createCategory(@RequestBody CategoryModel categoryModel){
        return categoryService.save(categoryModel);
}
@PostMapping("{category_id}")
    public CategoryModel updateCategory(@RequestBody CategoryModel categoryModel, @PathVariable long category_id){
        return categoryService.updateCategory(categoryModel,category_id);
}

@DeleteMapping("{category_id}")
public void deleteCategory(@PathVariable Long category_id){
        categoryService.deleteCategoryById(category_id);
}
}
