package com.budgetninja.back.controller;

import com.budgetninja.back.model.TransactionModel;
import com.budgetninja.back.service.TransactionService;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("")
    public List<TransactionModel> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/user/{userId}")
    public List<TransactionModel> getAllTransactionsByUserId(@PathVariable long userId) {
        return transactionService.getAllTransactionsByUserId(userId);
    }

    @GetMapping("/{id}")
    public TransactionModel getTransactionById(@PathVariable long id) {
        return transactionService.findById(id);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<TransactionModel> createTransaction(@RequestBody TransactionModel transaction, @PathVariable long userId) {
        return transactionService.addTransactionToUser(userId, transaction);
    }

    @PutMapping("/{id}")
    public TransactionModel updateTransaction(@RequestBody TransactionModel transaction, @PathVariable long id) {
        return transactionService.update(transaction, id);
    }
    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable long id) {
        transactionService.deleteById(id);
    }
}
