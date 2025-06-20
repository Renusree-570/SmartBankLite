package com.smartbanklite.smartbanklite.service.impl;

import com.smartbanklite.smartbanklite.exception.BankException;
import com.smartbanklite.smartbanklite.model.Account;
import com.smartbanklite.smartbanklite.model.Customer;
import com.smartbanklite.smartbanklite.repo.AccountRepository;
import com.smartbanklite.smartbanklite.repo.CustomerRepository;
import com.smartbanklite.smartbanklite.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    public Optional<Account> createAccount(Long CustomerId,Account account)
    {
        try
        {
            Optional<Customer> customer=customerRepository.findById(CustomerId);
            if(customer.isPresent())
            {
                account.setCustomer(customer.get());
                account.setBalance(account.getBalance()!=null?account.getBalance():0.0);
                return Optional.of(accountRepository.save(account));
            }
            else {
                throw new BankException("Customer is not fount with CustomerId: "+CustomerId);
            }
        }
        catch (BankException e)
        {
            log.error("Error: ",e);
            throw e;
        }
    }
    public Optional<Account> getAccountByAccountId(Long accountId)
    {
        try
        {
            Optional<Account> account=accountRepository.findById(accountId);
            if(account.isPresent())
            {
                return account;
            }
            else
            {
                throw new BankException("Can't find Account with AccoundId: "+account);
            }
        }
        catch (BankException e)
        {
            log.error("Error: ",e);
            throw e;
        }
    }
    public Optional<List<Account>> getAccountByCustomerId(Long customerId)
    {
        try
        {
            Optional<List<Account>> accounts=accountRepository.findByCustomerId(customerId);
            if(!accounts.get().isEmpty())
            {
                return accounts;
            }
            else
            {
                throw new BankException("No accounts are assosiated with CustomerId: "+customerId);
            }
        }
        catch (BankException e)
        {
            log.error("Error: ",e);
            throw  e;
        }
    }
}
