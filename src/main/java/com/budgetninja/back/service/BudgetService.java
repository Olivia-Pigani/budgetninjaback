package com.budgetninja.back.service;

import com.budgetninja.back.model.BudgetModel;
import com.budgetninja.back.model.UserModel;
import com.budgetninja.back.repository.BudgetRepository;
import com.budgetninja.back.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BudgetService {


private final BudgetRepository budgetRepository;
private final UserRepository userRepository;
    public BudgetService(BudgetRepository budgetRepository, UserRepository userRepository) {
        this.budgetRepository = budgetRepository;
        this.userRepository = userRepository;
    }
    public List<BudgetModel> findAll(){
        return budgetRepository.findAll();
    }
    public BudgetModel findById(Long id){
        return budgetRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "BUDGET not found")
        );
    }

    public BudgetModel addBudgetToUser(Long userId, BudgetModel budget) {
        UserModel user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        budget.setUser(user);
        return budgetRepository.save(budget);
    }

    public BudgetModel getByUserId(Long id){
        BudgetModel existingBudget = budgetRepository.findByUserId(id);

        if(existingBudget == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "BUDGET not found");
        } else {
            return existingBudget;
        }
    }
    public BudgetModel update(BudgetModel budgetModel){
        return budgetRepository.save(budgetModel);
    }
    public void deleteById(Long id){
        budgetRepository.deleteById(id);
    }
}
