package com.smartbanklite.smartbanklite.service.impl;

import com.smartbanklite.smartbanklite.exception.BankException;
import com.smartbanklite.smartbanklite.model.Account;
import com.smartbanklite.smartbanklite.model.Transfer;
import com.smartbanklite.smartbanklite.repo.AccountRepository;
import com.smartbanklite.smartbanklite.repo.TransferRepository;
import com.smartbanklite.smartbanklite.service.TransferService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class TransferServiceImpl implements TransferService {

    private final AccountRepository accountRepository;
    private final TransferRepository transferRepository;

    @Transactional
    public Transfer transferFunds(Long toAccountId, Long fromAccountId, Double amount, Transfer transfer) {
        Account toAccount = accountRepository.findById(toAccountId)
                .orElseThrow(() -> new BankException("Destination account not found with id: " + toAccountId));
        Account fromAccount = accountRepository.findById(fromAccountId)
                .orElseThrow(() -> new BankException("Source account not found with id: " + fromAccountId));

        if (fromAccount.getBalance() < amount) {
            throw new BankException("Insufficient balance to transfer funds");
        }

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        accountRepository.save(fromAccount);

        toAccount.setBalance(toAccount.getBalance() + amount);
        accountRepository.save(toAccount);

        transfer.setToAccount(toAccount);
        transfer.setFromAccount(fromAccount);
        transfer.setAmount(amount);
        transfer.setDescription("Transferred to account with id: " + toAccountId);
        transfer.setTransactionType("DEBIT");
        transfer.setTimestamp(LocalDateTime.now());

        return transferRepository.save(transfer);
    }

    public List<Transfer> getTransactionByAccountId(Long accountId) {
        List<Transfer> transfers = transferRepository.findByFromAccountId(accountId)
                .orElseThrow(() -> new BankException("No transactions found for account id: " + accountId));
        if (transfers.isEmpty()) {
            throw new BankException("No transfers associated with account id: " + accountId);
        }
        return transfers;
    }

    public Transfer getTransactionByTransferId(Long transferId) {
        return transferRepository.findById(transferId)
                .orElseThrow(() -> new BankException("Transfer not found with id: " + transferId));
    }
}
