package com.smartbanklite.smartbanklite.service;

import com.smartbanklite.smartbanklite.model.Customer;

import java.util.List;

public interface CustomerService {
    Customer createCustomer(Customer customer);
    List<Customer> getAllCustomers();
    Customer getCustomer(Long id);
    Customer deleteCustomer(Long id);
    Customer updateCustomer(Long id, Customer customer);
}
