package com.smartbanklite.smartbanklite.controller;

import com.smartbanklite.smartbanklite.exception.BankException;
import com.smartbanklite.smartbanklite.model.Account;
import com.smartbanklite.smartbanklite.service.AccountService;
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
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/createAccount/{customerId}")
    public ResponseEntity<Optional<Account>> createAccount(@PathVariable Long customerId, @RequestBody Account account)
    {
        try
        {
            Optional<Account> accountCreated=accountService.createAccount(customerId,account);
            if(accountCreated.isPresent())
            {
                return new ResponseEntity<>(accountCreated, HttpStatus.OK);
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

    @GetMapping("/getAccountByAccountId/{accountId}")
    public ResponseEntity<Optional<Account>> getAccountByAccountId(@PathVariable Long accountId)
    {
        try
        {
            Optional<Account> account=accountService.getAccountByAccountId(accountId);
            if(account.isPresent())
            {
                return new ResponseEntity<>(account,HttpStatus.OK);
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

    @GetMapping("/getAccountByCustomerId/{customerId}")
    public ResponseEntity<Optional<List<Account>>> getAccountByCustomerId(@PathVariable Long customerId)
    {
        try
        {
            Optional<List<Account>> accounts=accountService.getAccountByCustomerId(customerId);
            if(!accounts.get().isEmpty())
            {
                return new ResponseEntity<>(accounts,HttpStatus.OK);
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
