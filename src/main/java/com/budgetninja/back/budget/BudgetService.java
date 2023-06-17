package com.budgetninja.back.budget;

import com.budgetninja.back.budget.Budget;
import com.budgetninja.back.user.User;
import com.budgetninja.back.budget.BudgetRepository;
import com.budgetninja.back.user.UserRepository;
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

    public List<Budget> findAll() {
        return budgetRepository.findAll();
    }

    public Budget findById(Long id) {
        return budgetRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Budget non trouvé")
        );
    }

    public ResponseEntity<Budget> addBudgetToUser(Long userId, Budget budget) {
        if (budget == null || budget.getBalance() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Données de budget invalides");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur non trouvé"));

        // Vérifier si un budget est déjà associé à l'utilisateur
        if (user.getBudget() != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Un budget est déjà associé à cet utilisateur");
        }

        budget.setUser(user);
        Budget savedBudget = budgetRepository.save(budget);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedBudget);
    }


    public Budget findByUserId(Long userId) {
        return budgetRepository.findByUser_Id(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Budget non trouvé"));
    }

    public Budget update(Budget budget, long id) {
        Budget existingBudget = budgetRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Budget non trouvé"));
        existingBudget.setBalance(budget.getBalance());
        return budgetRepository.save(existingBudget);
    }

    public ResponseEntity<Budget> deleteById(Long id) {
        Budget budget = budgetRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Budget non trouvé"));
        budgetRepository.delete(budget);
        return ResponseEntity.ok(budget);
    }
}