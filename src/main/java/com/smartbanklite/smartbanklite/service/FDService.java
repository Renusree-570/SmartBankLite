package com.smartbanklite.smartbanklite.service;

import com.smartbanklite.smartbanklite.model.FDAccount;

import java.util.List;
import java.util.Optional;

public interface FDService {
    Optional<FDAccount> createFDAccount(Long CustomerId,FDAccount fdAccount);
    Optional<FDAccount> getFDAccountByFDId(Long fdId);
    Optional<List<FDAccount>> getFDAccountByCustomerId(Long customerId);
}
