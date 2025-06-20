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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class TransferServiceImpl implements TransferService {

    private final AccountRepository accountRepository;
    private final TransferRepository transferRepository;

    public Optional<Transfer> transferFunds(Long toAccountId,Long fromAccountId, Double amount, Transfer transfer)
    {
        try
        {
            Account toAccount=accountRepository.findById(toAccountId).get();
            Account fromAccount=accountRepository.findById(fromAccountId).get();
            if(fromAccount.getBalance()<amount)
            {
                throw new BankException("InSufficient Amount to transfer fund");
            }
            else {
                fromAccount.setBalance(fromAccount.getBalance()-amount);
                accountRepository.save(fromAccount);

                toAccount.setBalance(toAccount.getBalance()+amount);
                accountRepository.save(toAccount);

               // List<Transfer> debit_credit=new ArrayList<>();
               // Transfer debitTransfer=new Transfer();
                transfer.setToAccount(toAccount);
                transfer.setFromAccount(fromAccount);
                transfer.setAmount(amount);
                transfer.setDescription("Transferred to account with accountId:"+toAccountId);
                transfer.setTransactionType("DEBIT");
                transfer.setTimestamp(LocalDateTime.now());



//                Transfer creditTransfer=new Transfer();
//                creditTransfer.setAccount(toAccount);
//                creditTransfer.setAmount(amount);
//                creditTransfer.setDescription("Transferred from account with accountId:"+fromAccountId);
//                creditTransfer.setTransactionType("CREDIT");
//                creditTransfer.setTimestamp(LocalDateTime.now());

//                debit_credit.add(debitTransfer);
//                debit_credit.add(creditTransfer);

                return Optional.of(transfer);
            }

        }
        catch (BankException e)
        {
            log.error("Error: ",e);
            throw e;
        }
    }

    public Optional<List<Transfer>> getTransactionByAccountId(Long accountId)
    {
        try
        {
            Optional<List<Transfer>> transfers=transferRepository.findByFromAccountId(accountId);
            if(!transfers.get().isEmpty())
            {
                return transfers;
            }
            else {
                throw new BankException("No transfers associated with this account");
            }
        }
        catch (BankException e)
        {
            log.error("Error: ",e);
            throw e;
        }
    }

    public Optional<Transfer> getTransactionByTransferId(Long transferId)
    {
        try
        {
            Optional<Transfer> transfer=transferRepository.findById(transferId);
            if(transfer.isPresent())
            {
                return transfer;
            }
            else {
                throw new BankException("No Transfers with id: "+transferId);
            }
        }
        catch (BankException e)
        {
            log.error("Error: ",e);
            throw e;
        }
    }
}
