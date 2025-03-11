package com.clothingmarketplace.api.service;

import com.clothingmarketplace.api.entity.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction create(Transaction transaction);
    Transaction getById(String id);
    List<Transaction> getAll(String merchantId);
}
