package com.smartbanklite.smartbanklite;

import com.smartbanklite.smartbanklite.exception.BankException;
import com.smartbanklite.smartbanklite.model.Account;
import com.smartbanklite.smartbanklite.model.Customer;
import com.smartbanklite.smartbanklite.model.Transfer;
import com.smartbanklite.smartbanklite.repo.AccountRepository;
import com.smartbanklite.smartbanklite.repo.TransferRepository;
import com.smartbanklite.smartbanklite.service.impl.TransferServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransferServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private TransferRepository transferRepository;

    @InjectMocks
    private TransferServiceImpl transferService;

    private Account fromAccount;
    private Account toAccount;
    private Transfer transfer;

    @BeforeEach
    void setUp() {
        Customer customer = new Customer();
        customer.setId(1L);

        fromAccount = new Account();
        fromAccount.setId(1L);
        fromAccount.setAccountType("SAVINGS");
        fromAccount.setBalance(10000.0);
        fromAccount.setCustomer(customer);

        toAccount = new Account();
        toAccount.setId(2L);
        toAccount.setAccountType("SAVINGS");
        toAccount.setBalance(2000.0);
        toAccount.setCustomer(customer);

        transfer = new Transfer();
    }

    @Test
    void transferFunds_Success() {
        when(accountRepository.findById(2L)).thenReturn(Optional.of(toAccount));
        when(accountRepository.findById(1L)).thenReturn(Optional.of(fromAccount));
        when(accountRepository.save(any(Account.class))).thenReturn(fromAccount);
        when(transferRepository.save(any(Transfer.class))).thenReturn(transfer);

        Transfer result = transferService.transferFunds(2L, 1L, 3000.0, transfer);

        assertNotNull(result);
        assertEquals(7000.0, fromAccount.getBalance());
        assertEquals(5000.0, toAccount.getBalance());
        verify(transferRepository, times(1)).save(any(Transfer.class));
    }

    @Test
    void transferFunds_InsufficientBalance_ThrowsBankException() {
        when(accountRepository.findById(2L)).thenReturn(Optional.of(toAccount));
        when(accountRepository.findById(1L)).thenReturn(Optional.of(fromAccount));

        assertThrows(BankException.class, () -> transferService.transferFunds(2L, 1L, 50000.0, transfer));
        verify(transferRepository, never()).save(any());
    }

    @Test
    void transferFunds_SourceAccountNotFound_ThrowsBankException() {
        when(accountRepository.findById(2L)).thenReturn(Optional.of(toAccount));
        when(accountRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(BankException.class, () -> transferService.transferFunds(2L, 99L, 500.0, transfer));
    }

    @Test
    void getTransactionByTransferId_Success() {
        transfer.setId(1L);
        when(transferRepository.findById(1L)).thenReturn(Optional.of(transfer));

        Transfer result = transferService.getTransactionByTransferId(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void getTransactionByTransferId_NotFound_ThrowsBankException() {
        when(transferRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(BankException.class, () -> transferService.getTransactionByTransferId(99L));
    }
}

