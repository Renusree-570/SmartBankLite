package com.smartbanklite.smartbanklite.service;

import com.smartbanklite.smartbanklite.model.FDAccount;

import java.util.List;

public interface FDService {
    FDAccount createFDAccount(Long customerId, FDAccount fdAccount);
    FDAccount getFDAccountByFDId(Long fdId);
    List<FDAccount> getFDAccountByCustomerId(Long customerId);
}
