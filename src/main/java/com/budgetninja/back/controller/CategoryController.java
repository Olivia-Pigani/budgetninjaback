package com.budgetninja.back.controller;

import com.budgetninja.back.model.CategoryModel;
import com.budgetninja.back.service.CategoryService;
import org.springframework.web.bind.annotation.*;

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
        return categoryService.findAll();
}
@GetMapping("/{id}")
    public CategoryModel getCategoryById(Long id){
        return categoryService.findById(id);
}
@PostMapping("")
    public CategoryModel createCategory(@RequestBody CategoryModel categoryModel){
        return categoryService.save(categoryModel);
}
@PostMapping("{id}")
    public CategoryModel updateCategory(@RequestBody CategoryModel categoryModel, @PathVariable long id){
        return categoryService.update(categoryModel,id);
}

@DeleteMapping("{id}")
public void deleteCategory(@PathVariable Long id){
        categoryService.deleteById(id);
}

}
