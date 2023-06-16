package com.budgetninja.back.controller;

import com.budgetninja.back.model.TransactionModel;
import com.budgetninja.back.service.TransactionService;
import jakarta.persistence.Table;
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
        return transactionService.findAll();
    }

    @GetMapping("/{id}")
    public TransactionModel getTransactionById(long id) {
        return transactionService.findById(id);
    }

    @PostMapping("")
    public TransactionModel createTransaction(@RequestBody TransactionModel transaction) {
        return transactionService.save(transaction);
    }

    @PutMapping("/{id}")
    public TransactionModel updateTransaction(@RequestBody TransactionModel transaction, @PathVariable long id) {
        return transactionService.update(transaction, id);
    }

    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable long id) {
        transactionService.deleteById(id);
    }h
}
