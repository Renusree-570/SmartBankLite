package com.smartbanklite.smartbanklite;

import com.smartbanklite.smartbanklite.exception.BankException;
import com.smartbanklite.smartbanklite.model.Customer;
import com.smartbanklite.smartbanklite.repo.CustomerRepository;
import com.smartbanklite.smartbanklite.service.impl.CustomerServiceImpl;
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
class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer.setId(1L);
        customer.setFullName("Jane Doe");
        customer.setEmail("jane@example.com");
        customer.setAddress("456 Park Ave");
        customer.setPhoneNumber("9123456780");
    }

    @Test
    void createCustomer_Success() {
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        Customer result = customerService.createCustomer(customer);

        assertNotNull(result);
        assertEquals("Jane Doe", result.getFullName());
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    void getAllCustomers_Success() {
        when(customerRepository.findAll()).thenReturn(List.of(customer));

        List<Customer> result = customerService.getAllCustomers();

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void getAllCustomers_EmptyList_ThrowsBankException() {
        when(customerRepository.findAll()).thenReturn(List.of());

        assertThrows(BankException.class, () -> customerService.getAllCustomers());
    }

    @Test
    void getCustomer_Success() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        Customer result = customerService.getCustomer(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void getCustomer_NotFound_ThrowsBankException() {
        when(customerRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(BankException.class, () -> customerService.getCustomer(99L));
    }

    @Test
    void deleteCustomer_Success() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        doNothing().when(customerRepository).deleteById(1L);

        Customer result = customerService.deleteCustomer(1L);

        assertNotNull(result);
        verify(customerRepository, times(1)).deleteById(1L);
    }

    @Test
    void updateCustomer_Success() {
        Customer updated = new Customer();
        updated.setFullName("Jane Updated");

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        Customer result = customerService.updateCustomer(1L, updated);

        assertNotNull(result);
        assertEquals("Jane Updated", result.getFullName());
    }
}

