package com.budgetninja.back.budget;


import org.springframework.http.ResponseEntity;
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
    public List<Budget> findAll() {
        return budgetService.findAll();
    }

    @GetMapping("{id}")
    public Budget findById(@PathVariable Long id) {
        return budgetService.findById(id);
    }

    @GetMapping("/user/{userId}")
    public Budget findByUserId(@PathVariable Long userId) {
        return budgetService.findByUserId(userId);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Budget> addBudgetToUser(@RequestBody Budget budget, @PathVariable Long userId) {
        return budgetService.addBudgetToUser(userId, budget);
    }

    @PutMapping("/{id}")
    public Budget update(@RequestBody Budget budget, @PathVariable Long id) {
        return budgetService.update(budget, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Budget> delete(@PathVariable Long id) {
        return budgetService.deleteById(id);
    }
}