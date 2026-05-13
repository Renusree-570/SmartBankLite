package com.smartbanklite.smartbanklite.controller;

import com.smartbanklite.smartbanklite.model.Account;
import com.smartbanklite.smartbanklite.service.AccountService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/{customerId}")
    public ResponseEntity<Account> createAccount(@PathVariable Long customerId, @Valid @RequestBody Account account) {
        return new ResponseEntity<>(accountService.createAccount(customerId, account), HttpStatus.CREATED);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<Account> getAccountByAccountId(@PathVariable Long accountId) {
        return ResponseEntity.ok(accountService.getAccountByAccountId(accountId));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Account>> getAccountByCustomerId(@PathVariable Long customerId) {
        return ResponseEntity.ok(accountService.getAccountByCustomerId(customerId));
    }
}
