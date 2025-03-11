package com.clothingmarketplace.api.service.impl;

import com.clothingmarketplace.api.entity.TransactionDetail;
import com.clothingmarketplace.api.repository.TransactionDetailRepository;
import com.clothingmarketplace.api.service.TransactionDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionDetailServiceImpl implements TransactionDetailService {
    private final TransactionDetailRepository transactionDetailRepository;

    @Override
    public List<TransactionDetail> createBulk(List<TransactionDetail> transactionDetails) {
        return transactionDetailRepository.saveAllAndFlush(transactionDetails);
    }
}
