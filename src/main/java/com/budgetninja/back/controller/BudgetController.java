package com.budgetninja.back.controller;


import com.budgetninja.back.model.BudgetModel;
import com.budgetninja.back.service.BudgetService;
import jakarta.persistence.Table;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/budgets")
public class BudgetController {
    private final BudgetService budgetService;
    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }
    @GetMapping("")
    public List<BudgetModel> findAll(){
        return budgetService.findAll();
    }

    @GetMapping("{id}")
    public BudgetModel findById(@PathVariable Long id){
        return budgetService.findById(id);
    }

    @PostMapping("/{userId}")
    public BudgetModel addBudgetToUser(@RequestBody BudgetModel budgetModel, @PathVariable Long userId){
        return budgetService.addBudgetToUser(userId, budgetModel);
    }

    @PutMapping("/{id}")
    public BudgetModel update(@RequestBody BudgetModel budgetModel, @PathVariable Long id){
        return budgetService.update(budgetModel, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        budgetService.deleteById(id);


}}
