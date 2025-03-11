package com.clothingmarketplace.api.service;

import com.clothingmarketplace.api.entity.Merchant;

import java.util.List;

public interface MerchantService {
    Merchant create(Merchant merchant);
    Merchant getById(String id);
    List<Merchant> getAll();
    Merchant update(Merchant merchant);
    void delete(String id);
}
