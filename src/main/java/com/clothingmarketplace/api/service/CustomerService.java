package com.clothingmarketplace.api.service;

import com.clothingmarketplace.api.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer create(Customer customer);
    Customer getById(String id);
    List<Customer> getAll();
    Customer update(Customer customer);
    void delete(String id);
}
