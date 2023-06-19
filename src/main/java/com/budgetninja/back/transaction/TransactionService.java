package com.budgetninja.back.transaction;

import com.budgetninja.back.category.Category;
import com.budgetninja.back.category.CategoryRepository;
import com.budgetninja.back.transaction.Transaction;
import com.budgetninja.back.user.User;
import com.budgetninja.back.transaction.TransactionRepository;
import com.budgetninja.back.user.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public TransactionService(TransactionRepository transactionRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public List<Transaction> getAllTransactionsByUserId(Long userId) {
        return transactionRepository.findAllByBudgetUser_Id(userId);
    }

    public Transaction findById(long id) {
        return transactionRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction not found")
        );
    }

    public ResponseEntity<Transaction> addTransactionToUser(Long userId, Transaction transaction) {
        if (transaction == null || transaction.getAmount() <= 0 || transaction.getDate() == null || transaction.getType() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Données de la transaction invalides");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur non trouvé"));

        transaction.setBudget(user.getBudget());
        if(transaction.getCategory() != null) {
            Category category = categoryRepository.findByName(transaction.getCategory().getName()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categorie non trouvée"));
            transaction.setCategory(category);
        } else {
            transaction.setCategory(null);
        }
        Transaction savedTransaction = transactionRepository.save(transaction);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedTransaction);
    }

    public ResponseEntity<Transaction> addCategoryToTransaction(long categoryId, long transactionId) {


        Transaction existingTransaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction non trouvée"));

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categorie non trouvée"));

        existingTransaction.setCategory(category);
        Transaction savedTransaction = transactionRepository.save(existingTransaction);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedTransaction);
    }

    public Transaction update(Transaction transaction, long id) {
        Transaction existingTransaction = transactionRepository.findById(id).orElse(null);
        if (existingTransaction == null) {
            throw new RuntimeException("Transaction not found");
        } else {
            existingTransaction.setDate(transaction.getDate());
            existingTransaction.setDescription(transaction.getDescription());
            existingTransaction.setAmount(transaction.getAmount());
            existingTransaction.setType(transaction.getType());
            if(transaction.getCategory() != null){
                Category category = categoryRepository.findByName(transaction.getCategory().getName()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categorie non trouvée"));
                existingTransaction.setCategory(category);
            } else {
                existingTransaction.setCategory(null);
            }
            return transactionRepository.save(existingTransaction);
        }
    }

    public void deleteById(long id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction not found"));

        try {
            transactionRepository.delete(transaction);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete transaction");
        }
    }
}
