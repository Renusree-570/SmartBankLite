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
import java.util.Optional;
@Slf4j
@Service
@AllArgsConstructor
public class FDServiceImpl implements FDService {

    private final CustomerRepository customerRepository;
    private final FDRepository fdRepository;

    public Optional<FDAccount> createFDAccount(Long customerId, FDAccount fdAccount)
    {
        try
        {
            Optional<Customer> customer=customerRepository.findById(customerId);
            if(customer.isPresent())
            {
                fdAccount.setCustomer(customer.get());
                fdAccount.setStartDate(LocalDate.now());
                fdAccount.setMaturityDate(LocalDate.now().plusMonths(fdAccount.getTenureMonths()));
                double maturityAmount=fdAccount.getPrincipalAmount()+(fdAccount.getPrincipalAmount()*(fdAccount.getInterestRate()/100)*(fdAccount.getTenureMonths()/12.0));
                fdAccount.setMaturityAmount(maturityAmount);

                return Optional.of(fdRepository.save(fdAccount));
            }
            else {
                throw new BankException("Customer with customerId: "+customerId+" is not present.");
            }
        }
        catch (BankException e)
        {
            log.error("Error: ",e);
            throw e;
        }
    }

    public Optional<FDAccount> getFDAccountByFDId(Long fdId)
    {
        try
        {
            Optional<FDAccount> fdAccount=fdRepository.findById(fdId);
            if(fdAccount.isPresent())
            {
                return fdAccount;
            }
            else
            {
                throw new BankException("Can't find the fdAccount with fdId: "+fdId);
            }
        }
        catch(BankException e)
        {
            log.error("Error: ",e);
            throw e;
        }
    }

    public Optional<List<FDAccount>> getFDAccountByCustomerId(Long customerId)
    {
        try
        {
            Optional<List<FDAccount>> fdAccounts=fdRepository.findByCustomerId(customerId);
            if(!fdAccounts.get().isEmpty())
            {
                return fdAccounts;
            }
            else
            {
                throw new BankException("No FDAccounts associated with this customerId: "+customerId);
            }
        }
        catch (BankException e)
        {
            log.error("Error: ",e);
            throw e;
        }
    }
}
