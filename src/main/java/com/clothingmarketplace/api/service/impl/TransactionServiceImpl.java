package com.clothingmarketplace.api.service.impl;

import com.clothingmarketplace.api.entity.*;
import com.clothingmarketplace.api.repository.TransactionRepository;
import com.clothingmarketplace.api.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionDetailService transactionDetailService;
    private final CustomerService customerService;
    private final MerchantService merchantService;
    private final ProductService productService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Transaction create(Transaction transaction) {
        Customer customer = customerService.getById(transaction.getCustomer().getId());
        Merchant merchant = merchantService.getById(transaction.getMerchant().getId());

        Transaction trx = Transaction.builder()
                .customer(customer)
                .merchant(merchant)
                .transactionDate(new Date())
                .build();
        transactionRepository.saveAndFlush(trx);

        List<TransactionDetail> trxDetail = transaction.getTransactionDetails().stream().map(detailRequest -> {
            Product product = productService.getById(detailRequest.getProduct().getId());
            if (product.getStock() - detailRequest.getQty() < 0) throw new RuntimeException("Sold Out");

            product.setStock(product.getStock() - detailRequest.getQty());

            return TransactionDetail.builder()
                    .product(product)
                    .transaction(trx)
                    .qty(detailRequest.getQty())
                    .productPrice(product.getPrice())
                    .build();
        }).toList();

        transactionDetailService.createBulk(trxDetail);
        trx.setTransactionDetails(trxDetail);

        Integer customerPoint = customer.getPoint() + 5;
        customer.setPoint(customerPoint);
        customerService.update(customer);

        return Transaction.builder()
                .id(trx.getId())
                .customer(trx.getCustomer())
                .merchant(trx.getMerchant())
                .transactionDate(trx.getTransactionDate())
                .transactionDetails(trxDetail)
                .build();
    }

    @Override
    public Transaction getById(String id) {
        return transactionRepository.findById(id).orElse(null);
    }

    @Override
    public List<Transaction> getAll(String merchantId) {
        return transactionRepository.findAll();
    }
}
