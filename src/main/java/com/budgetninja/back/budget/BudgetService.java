package com.budgetninja.back.budget;

import com.budgetninja.back.budget.Budget;
import com.budgetninja.back.project.ProjectRepository;
import com.budgetninja.back.saving.SavingRepository;
import com.budgetninja.back.transaction.TransactionRepository;
import com.budgetninja.back.user.User;
import com.budgetninja.back.budget.BudgetRepository;
import com.budgetninja.back.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BudgetService {
    private final BudgetRepository budgetRepository;
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;
    private final SavingRepository savingRepository;
    private final ProjectRepository projectRepository;

    public BudgetService(BudgetRepository budgetRepository, UserRepository userRepository, TransactionRepository transactionRepository, SavingRepository savingRepository, ProjectRepository projectRepository) {
        this.budgetRepository = budgetRepository;
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
        this.savingRepository = savingRepository;
        this.projectRepository = projectRepository;
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

    @Transactional
    public void deleteById(Long id) {
        Budget budget = budgetRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Budget non trouvé"));
        transactionRepository.deleteAllByBudget_Id(id);
        savingRepository.deleteAllByBudget_Id(id);
        projectRepository.deleteAllBySavingBudget_Id(id);
        budgetRepository.delete(budget);
    }
}