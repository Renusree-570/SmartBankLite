package com.smartbanklite.smartbanklite.service;

import com.smartbanklite.smartbanklite.model.Account;

import java.util.List;

public interface AccountService {
    Account createAccount(Long customerId, Account account);
    List<Account> getAccountByCustomerId(Long customerId);
    Account getAccountByAccountId(Long accountId);
}
