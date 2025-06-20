package com.smartbanklite.smartbanklite.controller;

import com.smartbanklite.smartbanklite.exception.BankException;
import com.smartbanklite.smartbanklite.model.Customer;
import com.smartbanklite.smartbanklite.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@RestController
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/post")
    public ResponseEntity<Optional<Customer>> post(@RequestBody Customer customer)
    {
        try
        {
            Optional<Customer> customerCreated=customerService.createCustomer(customer);
            if(customerCreated.isPresent())
            {
                return new ResponseEntity<>(customerCreated, HttpStatus.OK);
            }
            else
            {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        catch (BankException e)
        {
            log.error("Error: ",e.getMessage());
            throw e;
        }
    }

    @GetMapping("/get")
    public ResponseEntity<Optional<List<Customer>>> get()
    {
        try
        {
            Optional<List<Customer>> customerList=customerService.getAllCustomers();
            if(!customerList.get().isEmpty())
            {
                return new ResponseEntity<>(customerList,HttpStatus.OK);
            }
            else
            {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
      catch (BankException e)
      {
          log.error("Error: ",e.getMessage());
          throw e;
      }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Optional<Customer>> get(@PathVariable Long id)
    {
        try
        {
            Optional<Customer> customer=customerService.getCustomer(id);
            if(customer.isPresent())
            {
                return new ResponseEntity<>(customer,HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        catch (BankException e)
        {
            log.error("Error: ",e);
            throw e;
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Optional<Customer>> delete(@PathVariable Long id)
    {
        try
        {
            Optional<Customer> customer=customerService.deleteCustomer(id);
            if(customer.isPresent())
            {
                return new ResponseEntity<>(customer,HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        catch (BankException e)
        {
            log.error("Error: ",e.getMessage());
            throw e;
        }
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<Optional<Customer>> put(@PathVariable Long id,@RequestBody Customer customer)
    {
        try
        {
            Optional<Customer> customerUpdated=customerService.updateCustomer(id,customer);
            if(customerUpdated.isPresent())
            {
                return new ResponseEntity<>(customerUpdated,HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        catch (BankException e)
        {
            log.error("Error: ",e.getMessage());
            throw e;
        }
    }
}
