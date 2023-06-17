package com.budgetninja.back.service;

import com.budgetninja.back.model.BudgetModel;
import com.budgetninja.back.model.TransactionModel;
import com.budgetninja.back.model.UserModel;
import com.budgetninja.back.repository.TransactionRepository;
import com.budgetninja.back.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public TransactionService(TransactionRepository transactionRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    public List<TransactionModel> getAllTransactionsByUserId(Long userId) {
        return transactionRepository.findAllByBudgetUser_Id(userId);
    }

    public TransactionModel findById(long id) {
        return transactionRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction not found")
        );
    }

    public ResponseEntity<TransactionModel> addTransactionToUser(Long userId, TransactionModel transaction) {
        if (transaction == null || transaction.getAmount() <= 0 || transaction.getDate() == null ||  transaction.getType() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Données de la transaction invalides");
        }

        UserModel user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur non trouvé"));

        transaction.setBudget(user.getBudget());
        TransactionModel savedTransaction = transactionRepository.save(transaction);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedTransaction);
    }

    public TransactionModel update(TransactionModel transaction, long id) {
        TransactionModel existingTransaction = transactionRepository.findById(id).orElse(null);
        if (existingTransaction == null) {
            throw new RuntimeException("Transaction not found");
        } else {
            existingTransaction.setDate(transaction.getDate());
            existingTransaction.setDescription(transaction.getDescription());
            existingTransaction.setAmount(transaction.getAmount());
            existingTransaction.setType(transaction.getType());
            existingTransaction.setBudget(transaction.getBudget());
            existingTransaction.setCategory(transaction.getCategory());
            return transactionRepository.save(existingTransaction);
        }
    }

    public void deleteById(long id) {
        transactionRepository.deleteById(id);
    }
}
