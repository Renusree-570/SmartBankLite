package com.smartbanklite.smartbanklite.controller;

import com.smartbanklite.smartbanklite.model.Transfer;
import com.smartbanklite.smartbanklite.service.TransferService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/transfers")
public class TransferController {

    private final TransferService transferService;

    @PostMapping
    public ResponseEntity<Transfer> transferFund(
            @RequestParam Long toAccountId,
            @RequestParam Long fromAccountId,
            @RequestParam Double amount,
            @RequestBody Transfer transfer) {
        return new ResponseEntity<>(transferService.transferFunds(toAccountId, fromAccountId, amount, transfer), HttpStatus.CREATED);
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<Transfer>> getTransactionByAccountId(@PathVariable Long accountId) {
        return ResponseEntity.ok(transferService.getTransactionByAccountId(accountId));
    }

    @GetMapping("/{transferId}")
    public ResponseEntity<Transfer> getTransactionByTransferId(@PathVariable Long transferId) {
        return ResponseEntity.ok(transferService.getTransactionByTransferId(transferId));
    }
}
