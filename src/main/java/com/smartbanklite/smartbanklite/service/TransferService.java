package com.smartbanklite.smartbanklite.service;

import com.smartbanklite.smartbanklite.model.Transfer;

import java.util.List;
import java.util.Optional;

public interface TransferService {
    Optional<Transfer> transferFunds(Long toAccountId, Long fromAccountId, Double amount, Transfer transfer);
    Optional<List<Transfer>> getTransactionByAccountId(Long AccountId);
    Optional<Transfer> getTransactionByTransferId(Long TransferId);
}
