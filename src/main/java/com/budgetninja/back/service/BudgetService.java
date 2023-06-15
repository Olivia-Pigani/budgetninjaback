package com.budgetninja.back.service;

import com.budgetninja.back.model.BudgetModel;
import com.budgetninja.back.repository.BudgetRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BudgetService {


private final BudgetRepository budgetRepository;

    public BudgetService(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }


    public List<BudgetModel> findAll(){
        return budgetRepository.findAll();
    }


    public BudgetModel findById(Long id){
        return budgetRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "BUDGET not found")
        );
    }


    public BudgetModel update(BudgetModel budgetModel){
        return budgetRepository.save(budgetModel);
    }



    public void deleteById(Long id){
        budgetRepository.deleteById(id);
    }



}
