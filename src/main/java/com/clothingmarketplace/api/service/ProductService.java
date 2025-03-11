package com.clothingmarketplace.api.service;

import com.clothingmarketplace.api.entity.Product;

import java.util.List;

public interface ProductService {
    Product create(Product product);
    Product getById(String id);
    List<Product> getAll();
    Product update(Product product);
    void delete(String id);
}
