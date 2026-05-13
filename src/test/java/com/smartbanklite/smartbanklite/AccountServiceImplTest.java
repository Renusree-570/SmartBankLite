package com.smartbanklite.smartbanklite;

import com.smartbanklite.smartbanklite.exception.BankException;
import com.smartbanklite.smartbanklite.model.Account;
import com.smartbanklite.smartbanklite.model.Customer;
import com.smartbanklite.smartbanklite.repo.AccountRepository;
import com.smartbanklite.smartbanklite.repo.CustomerRepository;
import com.smartbanklite.smartbanklite.service.impl.AccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private AccountServiceImpl accountService;

    private Customer customer;
    private Account account;

    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer.setId(1L);
        customer.setFullName("John Doe");
        customer.setEmail("john@example.com");
        customer.setAddress("123 Main St");
        customer.setPhoneNumber("9876543210");

        account = new Account();
        account.setId(1L);
        account.setAccountType("SAVINGS");
        account.setBalance(5000.0);
        account.setCustomer(customer);
    }

    @Test
    void createAccount_Success() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        Account result = accountService.createAccount(1L, account);

        assertNotNull(result);
        assertEquals("SAVINGS", result.getAccountType());
        assertEquals(5000.0, result.getBalance());
        verify(accountRepository, times(1)).save(any(Account.class));
    }

    @Test
    void createAccount_CustomerNotFound_ThrowsBankException() {
        when(customerRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(BankException.class, () -> accountService.createAccount(99L, account));
        verify(accountRepository, never()).save(any());
    }

    @Test
    void getAccountByAccountId_Success() {
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

        Account result = accountService.getAccountByAccountId(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void getAccountByAccountId_NotFound_ThrowsBankException() {
        when(accountRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(BankException.class, () -> accountService.getAccountByAccountId(99L));
    }

    @Test
    void getAccountByCustomerId_Success() {
        when(accountRepository.findByCustomerId(1L)).thenReturn(Optional.of(List.of(account)));

        List<Account> result = accountService.getAccountByCustomerId(1L);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }
}

