package com.smartbanklite.smartbanklite.service.impl;

import com.smartbanklite.smartbanklite.exception.BankException;
import com.smartbanklite.smartbanklite.model.Customer;
import com.smartbanklite.smartbanklite.model.FDAccount;
import com.smartbanklite.smartbanklite.repo.CustomerRepository;
import com.smartbanklite.smartbanklite.repo.FDRepository;
import com.smartbanklite.smartbanklite.service.FDService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class FDServiceImpl implements FDService {

    private final CustomerRepository customerRepository;
    private final FDRepository fdRepository;

    public FDAccount createFDAccount(Long customerId, FDAccount fdAccount) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new BankException("Customer not found with id: " + customerId));
        fdAccount.setCustomer(customer);
        fdAccount.setStartDate(LocalDate.now());
        fdAccount.setMaturityDate(LocalDate.now().plusMonths(fdAccount.getTenureMonths()));
        double maturityAmount = fdAccount.getPrincipalAmount()
                + (fdAccount.getPrincipalAmount() * (fdAccount.getInterestRate() / 100) * (fdAccount.getTenureMonths() / 12.0));
        fdAccount.setMaturityAmount(maturityAmount);
        return fdRepository.save(fdAccount);
    }

    public FDAccount getFDAccountByFDId(Long fdId) {
        return fdRepository.findById(fdId)
                .orElseThrow(() -> new BankException("FD Account not found with id: " + fdId));
    }

    public List<FDAccount> getFDAccountByCustomerId(Long customerId) {
        List<FDAccount> fdAccounts = fdRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new BankException("No FD accounts found for customer id: " + customerId));
        if (fdAccounts.isEmpty()) {
            throw new BankException("No FD accounts associated with customer id: " + customerId);
        }
        return fdAccounts;
    }
}
