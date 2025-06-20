package com.smartbanklite.smartbanklite.service.impl;

import com.smartbanklite.smartbanklite.exception.BankException;
import com.smartbanklite.smartbanklite.model.Customer;
import com.smartbanklite.smartbanklite.repo.CustomerRepository;
import com.smartbanklite.smartbanklite.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    
    public Optional<Customer> createCustomer (Customer customer)
    {
        try {
            if (customer != null) {
                Customer customerCreated = customerRepository.save(customer);
                return Optional.of(customerCreated);
            }
            else {
                throw new BankException("Can't create the customer");
            }
        }
        catch (BankException e)
        {
            log.error("Error: ",e.getMessage());
            throw e;
        }

    }

    public Optional<List<Customer>> getAllCustomers()
    {
        try
        {
            List<Customer> customerList=customerRepository.findAll();
            if(!customerList.isEmpty())
            {
                return Optional.ofNullable(customerList);
            }
            else {
                throw new BankException("CustomerList is Empty");
            }
        }
        catch (BankException e)
        {
            log.error("Error: ",e.getMessage());
            throw e;
        }
    }

    public Optional<Customer> getCustomer(Long id) {
        try {
            Optional<Customer> customer = customerRepository.findById(id);
            if (customer.isPresent()) {
                return customer;
            } else {
                throw new BankException("Customer with " + id + "is not present");
            }
        } catch (BankException e) {
            log.error("Error: ", e);
            throw e;
        }
    }

    public Optional<Customer> updateCustomer(Long id,Customer customer) {
        try {
            Optional<Customer> oldCustomer = customerRepository.findById(id);
            if (oldCustomer.isPresent()) {
                if (customer.getFullName() != null) {
                    oldCustomer.get().setFullName(customer.getFullName());
                }
                if (customer.getEmail() != null) {
                    oldCustomer.get().setEmail(customer.getEmail());
                }
                if (customer.getAddress() != null) {
                    oldCustomer.get().setAddress(customer.getAddress());
                }
                if (customer.getPhoneNumber() != null) {
                    oldCustomer.get().setPhoneNumber(customer.getPhoneNumber());
                }
                return oldCustomer;
            }
            else {
                throw new BankException("Can't find the Customer with id: " + id);
            }
        } catch (BankException e) {
            log.error("Error: ", e.getMessage());
            throw e;
        }
    }

    public Optional<Customer> deleteCustomer(Long id)
    {
        try
        {
            Optional<Customer> customer=customerRepository.findById(id);
            if(customer.isPresent())
            {
                customerRepository.deleteById(id);
                return customer;
            }
            else
            {
                throw new BankException("Can't find the book with id: "+id);
            }
        }
        catch (BankException e)
        {
            log.error("Error: ",e.getMessage());
            throw e;
        }
    }
}
