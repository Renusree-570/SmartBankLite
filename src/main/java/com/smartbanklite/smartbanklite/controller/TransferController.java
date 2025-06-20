package com.smartbanklite.smartbanklite.controller;

import com.smartbanklite.smartbanklite.exception.BankException;
import com.smartbanklite.smartbanklite.model.Transfer;
import com.smartbanklite.smartbanklite.service.TransferService;
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
public class TransferController {
    private final TransferService transferService;
@PostMapping("/transferFund")
    public ResponseEntity<Optional<Transfer>> transferFund(@RequestParam Long toAccountId,@RequestParam Long fromAccountId,@RequestParam Double amount,@RequestBody Transfer transfer)
    {
        try
        {
            Optional<Transfer> transfers=transferService.transferFunds(toAccountId,fromAccountId,amount,transfer);
            if(transfers.isPresent())
            {
                return new ResponseEntity<>(transfers, HttpStatus.OK);
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

    @GetMapping("/getTransactionByAccountId/{accountId}")
    public ResponseEntity<Optional<List<Transfer>>> getTransactionByAccountId(@PathVariable Long accountId)
    {
        try
        {
            Optional<List<Transfer>> transferList=transferService.getTransactionByAccountId(accountId);
            if(!transferList.get().isEmpty())
            {
                return new ResponseEntity<>(transferList,HttpStatus.OK);
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

    @GetMapping("/getTransactionByTransferId/{transferId}")
    public ResponseEntity<Optional<Transfer>> getTransactionByTransferId(@PathVariable Long transferId)
    {
        try
        {
            Optional<Transfer> transfer=transferService.getTransactionByTransferId(transferId);
            if(transfer.isPresent())
            {
                return new ResponseEntity<>(transfer,HttpStatus.OK);
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
