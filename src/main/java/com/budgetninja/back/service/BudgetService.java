package com.budgetninja.back.service;

import com.budgetninja.back.model.BudgetModel;
import com.budgetninja.back.model.UserModel;
import com.budgetninja.back.repository.BudgetRepository;
import com.budgetninja.back.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public List<BudgetModel> findAll() {
        return budgetRepository.findAll();
    }

    public BudgetModel findById(Long id) {
        return budgetRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Budget non trouvé")
        );
    }

    public ResponseEntity<BudgetModel> addBudgetToUser(Long userId, BudgetModel budget) {
        if (budget == null || budget.getBalance() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Données de budget invalides");
        }

        UserModel user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur non trouvé"));

        // Vérifier si un budget est déjà associé à l'utilisateur
        if (user.getBudget() != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Un budget est déjà associé à cet utilisateur");
        }

        budget.setUser(user);
        BudgetModel savedBudget = budgetRepository.save(budget);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedBudget);
    }


    public BudgetModel findByUserId(Long userId) {
        return budgetRepository.findByUser_Id(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Budget non trouvé"));
    }

    public BudgetModel update(BudgetModel budgetModel, long id) {
        BudgetModel existingBudget = budgetRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Budget non trouvé"));
        existingBudget.setBalance(budgetModel.getBalance());
        return budgetRepository.save(existingBudget);
    }

    public ResponseEntity<BudgetModel> deleteById(Long id) {
        BudgetModel budget = budgetRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Budget non trouvé"));
        budgetRepository.delete(budget);
        return ResponseEntity.ok(budget);
    }
}