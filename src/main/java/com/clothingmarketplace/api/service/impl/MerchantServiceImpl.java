package com.clothingmarketplace.api.service.impl;

import com.clothingmarketplace.api.entity.Merchant;
import com.clothingmarketplace.api.repository.MerchantRepository;
import com.clothingmarketplace.api.service.MerchantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantServiceImpl implements MerchantService {
    private final MerchantRepository merchantRepository;

    @Override
    public Merchant create(Merchant merchant) {
        return merchantRepository.saveAndFlush(merchant);
    }

    @Override
    public Merchant getById(String id) {
        return merchantRepository.findById(id).orElse(null);
    }

    @Override
    public List<Merchant> getAll() {
        return merchantRepository.findAll();
    }

    @Override
    public Merchant update(Merchant merchant) {
        return merchantRepository.saveAndFlush(merchant);
    }

    @Override
    public void delete(String id) {
        merchantRepository.deleteById(id);
    }
}
