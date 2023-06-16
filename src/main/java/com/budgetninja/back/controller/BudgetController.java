package com.budgetninja.back.controller;


import com.budgetninja.back.model.BudgetModel;
import com.budgetninja.back.service.BudgetService;
import jakarta.persistence.Table;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/budget")
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

    @PostMapping("")
    public BudgetModel save(@RequestBody BudgetModel budgetModel){
        return budgetService.update(budgetModel);
    }

    @PutMapping("")
    public BudgetModel update(@RequestBody BudgetModel budgetModel){
        return budgetService.update(budgetModel);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        budgetService.deleteById(id);


}}
