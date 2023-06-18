package com.budgetninja.back.transaction;

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
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/user/{userId}")
    public List<Transaction> getAllTransactionsByUserId(@PathVariable long userId) {
        return transactionService.getAllTransactionsByUserId(userId);
    }

    @GetMapping("/{id}")
    public Transaction getTransactionById(@PathVariable long id) {
        return transactionService.findById(id);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction, @PathVariable long userId) {
        return transactionService.addTransactionToUser(userId, transaction);
    }

    @PutMapping("/{transactionId}/category/{categoryId}")
    public ResponseEntity<Transaction> addCategoryToTransaction(@PathVariable long transactionId, @PathVariable long categoryId) {
        return transactionService.addCategoryToTransaction(categoryId, transactionId);
    }

    @PutMapping("/{id}")
    public Transaction updateTransaction(@RequestBody Transaction transaction, @PathVariable long id) {
        return transactionService.update(transaction, id);
    }

    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable long id) {

        transactionService.deleteById(id);
    }
}
