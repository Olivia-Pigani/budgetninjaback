package com.budgetninja.back.category;

import com.budgetninja.back.category.Category;
import com.budgetninja.back.category.CategoryRepository;
import com.budgetninja.back.transaction.Transaction;
import com.budgetninja.back.transaction.TransactionRepository;
import com.budgetninja.back.user.User;
import com.budgetninja.back.user.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;
    public CategoryService(CategoryRepository categoryRepository, UserRepository userRepository, TransactionRepository transactionRepository){
       this.categoryRepository = categoryRepository;
         this.userRepository = userRepository;
            this.transactionRepository = transactionRepository;
    }

    public List<Category> getAllCategories() {

        return categoryRepository.findAll();
    }

    public List<Category> getAllCategoriesByUserId(Long userId) {
        return categoryRepository.findAllByUserId(userId);
    }
    public Category findCategoryById(Long category_id){
        return categoryRepository.findById(category_id).orElseThrow(
                ()->new ResponseStatusException(HttpStatus.NOT_FOUND,"category non trouvée")
        );
    }

    public ResponseEntity<Category> addCategoryToUser(Long userId, Category category){
        if(category == null || category.getName().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Données de categorie invalides");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Utilisateur non trouvé"));
        category.setUser(user);
        Category savedCategory = categoryRepository.save(category);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedCategory);
    }

    public Category updateCategory(Category category, long category_id){
        Category existingCategory= categoryRepository.findById(category_id).orElse(null);
        if( existingCategory ==null){
            throw new RuntimeException("Categorie non trouvée");

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

