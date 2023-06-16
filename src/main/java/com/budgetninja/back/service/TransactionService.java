package com.budgetninja.back.service;

import com.budgetninja.back.model.TransactionModel;
import com.budgetninja.back.model.UserModel;
import com.budgetninja.back.repository.TransactionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<TransactionModel> findAll() {
        return transactionRepository.findAll();
    }

    public TransactionModel findById(long id) {
        return transactionRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction not found")
        );
    }

    public TransactionModel save(TransactionModel transaction) {
        return transactionRepository.save(transaction);
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
