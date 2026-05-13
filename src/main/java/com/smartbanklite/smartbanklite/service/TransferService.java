package com.smartbanklite.smartbanklite.service;

import com.smartbanklite.smartbanklite.model.Transfer;

import java.util.List;

public interface TransferService {
    Transfer transferFunds(Long toAccountId, Long fromAccountId, Double amount, Transfer transfer);
    List<Transfer> getTransactionByAccountId(Long accountId);
    Transfer getTransactionByTransferId(Long transferId);
}
