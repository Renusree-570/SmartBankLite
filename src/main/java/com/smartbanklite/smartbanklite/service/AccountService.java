package com.smartbanklite.smartbanklite.service;

import com.smartbanklite.smartbanklite.model.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    Optional<Account> createAccount(Long CustomerId,Account account);
    Optional<List<Account>> getAccountByCustomerId(Long CustomerId);
    Optional<Account> getAccountByAccountId(Long AccountId);

    }
