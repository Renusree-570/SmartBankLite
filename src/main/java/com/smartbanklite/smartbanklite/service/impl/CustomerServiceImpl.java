package com.smartbanklite.smartbanklite.service.impl;

import com.smartbanklite.smartbanklite.exception.BankException;
import com.smartbanklite.smartbanklite.model.Customer;
import com.smartbanklite.smartbanklite.repo.CustomerRepository;
import com.smartbanklite.smartbanklite.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customerList = customerRepository.findAll();
        if (customerList.isEmpty()) {
            throw new BankException("No customers found");
        }
        return customerList;
    }

    public Customer getCustomer(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new BankException("Customer not found with id: " + id));
    }

    public Customer updateCustomer(Long id, Customer customer) {
        Customer existing = customerRepository.findById(id)
                .orElseThrow(() -> new BankException("Customer not found with id: " + id));
        if (customer.getFullName() != null) existing.setFullName(customer.getFullName());
        if (customer.getEmail() != null) existing.setEmail(customer.getEmail());
        if (customer.getAddress() != null) existing.setAddress(customer.getAddress());
        if (customer.getPhoneNumber() != null) existing.setPhoneNumber(customer.getPhoneNumber());
        return customerRepository.save(existing);
    }

    public Customer deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new BankException("Customer not found with id: " + id));
        customerRepository.deleteById(id);
        return customer;
    }
}
