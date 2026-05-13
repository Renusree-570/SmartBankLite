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

@Slf4j
@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    public Account createAccount(Long customerId, Account account) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new BankException("Customer not found with id: " + customerId));
        account.setCustomer(customer);
        account.setBalance(account.getBalance() != null ? account.getBalance() : 0.0);
        return accountRepository.save(account);
    }

    public Account getAccountByAccountId(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new BankException("Account not found with id: " + accountId));
    }

    public List<Account> getAccountByCustomerId(Long customerId) {
        List<Account> accounts = accountRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new BankException("No accounts found for customer id: " + customerId));
        if (accounts.isEmpty()) {
            throw new BankException("No accounts associated with customer id: " + customerId);
        }
        return accounts;
    }
}
