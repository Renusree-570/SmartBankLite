package com.smartbanklite.smartbanklite.repo;

import com.smartbanklite.smartbanklite.model.FDAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FDRepository extends JpaRepository<FDAccount,Long> {
    Optional<List<FDAccount>> findByCustomerId(Long customerId);
}
