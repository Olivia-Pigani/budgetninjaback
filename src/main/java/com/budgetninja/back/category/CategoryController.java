package com.budgetninja.back.category;

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
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
}
@GetMapping("/{category_id}")
    public Category getCategoryById(@PathVariable Long category_id){
        return categoryService.findCategoryById(category_id);
}
@PostMapping("")
    public Category createCategory(@RequestBody Category category){
        return categoryService.save(category);
}
@PostMapping("{category_id}")
    public Category updateCategory(@RequestBody Category category, @PathVariable long category_id){
        return categoryService.updateCategory(category,category_id);
}

@DeleteMapping("{category_id}")
public void deleteCategory(@PathVariable Long category_id){
        categoryService.deleteCategoryById(category_id);
}
}
