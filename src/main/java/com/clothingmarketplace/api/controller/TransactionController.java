package com.clothingmarketplace.api.controller;

import com.clothingmarketplace.api.entity.Transaction;
import com.clothingmarketplace.api.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping
    private ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
        Transaction newTransaction = transactionService.create(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTransaction);
    }

    @GetMapping("/{merchantId}")
    private ResponseEntity<List<Transaction>> getTransactions(@PathVariable String merchantId) {
        List<Transaction> transactions = transactionService.getAll(merchantId);
        return ResponseEntity.status(HttpStatus.OK).body(transactions);
    }
}
