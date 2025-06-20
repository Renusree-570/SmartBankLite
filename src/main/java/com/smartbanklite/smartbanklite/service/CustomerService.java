package com.smartbanklite.smartbanklite.service;

import com.smartbanklite.smartbanklite.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    public Optional<Customer> createCustomer(Customer customer);
    public Optional<List<Customer>> getAllCustomers();
    public Optional<Customer> getCustomer(Long id);
    public Optional<Customer> deleteCustomer(Long id);
    public Optional<Customer> updateCustomer(Long id,Customer customer);
}
