package com.smartbanklite.smartbanklite.controller;

import com.smartbanklite.smartbanklite.model.FDAccount;
import com.smartbanklite.smartbanklite.service.FDService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/fd-accounts")
public class FDAccountController {

    private final FDService fdService;

    @PostMapping("/{customerId}")
    public ResponseEntity<FDAccount> createFDAccount(@PathVariable Long customerId, @Valid @RequestBody FDAccount fdAccount) {
        return new ResponseEntity<>(fdService.createFDAccount(customerId, fdAccount), HttpStatus.CREATED);
    }

    @GetMapping("/{fdId}")
    public ResponseEntity<FDAccount> getFDAccountByFDId(@PathVariable Long fdId) {
        return ResponseEntity.ok(fdService.getFDAccountByFDId(fdId));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<FDAccount>> getFDAccountByCustomerId(@PathVariable Long customerId) {
        return ResponseEntity.ok(fdService.getFDAccountByCustomerId(customerId));
    }
}
