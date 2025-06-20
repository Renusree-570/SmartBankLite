package com.smartbanklite.smartbanklite.controller;

import com.smartbanklite.smartbanklite.exception.BankException;
import com.smartbanklite.smartbanklite.model.FDAccount;
import com.smartbanklite.smartbanklite.repo.FDRepository;
import com.smartbanklite.smartbanklite.service.FDService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@Slf4j
@RestController
@AllArgsConstructor
public class FDAccountController {

    private final FDService fdService;

    @PostMapping("/createFDAccount/{customerId}")
    public ResponseEntity<Optional<FDAccount>> createAccount(@PathVariable Long customerId, @RequestBody FDAccount fdAccount)
    {
        try
        {
            Optional<FDAccount> fdAccountCreated=fdService.createFDAccount(customerId,fdAccount);
            if(fdAccountCreated.isPresent())
            {
                return new ResponseEntity<>(fdAccountCreated, HttpStatus.OK);
            }
            else
            {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        catch(BankException e)
        {
            log.error("Error: ",e);
            throw e;
        }
    }

    @GetMapping("/getFDAccountByFDId/{FDId}")
    public ResponseEntity<Optional<FDAccount>> getFDAccountByFDId(@PathVariable Long FDId)
    {
        try
        {
            Optional<FDAccount> fdAccount= fdService.getFDAccountByFDId(FDId);
            if(fdAccount.isPresent())
            {
                return new ResponseEntity<>(fdAccount,HttpStatus.OK);
            }
            else
            {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        catch (BankException e)
        {
            log.error("Error: ",e);
            throw e;
        }
    }

    @GetMapping("/getFDAccountByCustomerId/{customerId}")
    public ResponseEntity<Optional<List<FDAccount>>> getFDAccountByCustomerId(@PathVariable Long customerId)
    {
        try
        {
            Optional<List<FDAccount>> fdAccounts=fdService.getFDAccountByCustomerId(customerId);
            if(!fdAccounts.get().isEmpty())
            {
                return new ResponseEntity<>(fdAccounts,HttpStatus.OK);
            }
            else
            {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        catch (BankException e)
        {
            log.error("Error: ",e);
            throw e;
        }
    }
}
