package com.cafe.service.core.table;

import com.cafe.entity.table.CafeTableAssignedToWaiter;

import java.util.List;
import java.util.Optional;

public interface CafeTableAssignedToWaiterService {

    CafeTableAssignedToWaiter create(CafeTableAssignedToWaiterCreationParams params);

    Optional<CafeTableAssignedToWaiter> findById(Long id);

    CafeTableAssignedToWaiter getById(Long id);

    List<CafeTableAssignedToWaiter> findAllByWaiterId(Long id);

    Optional<CafeTableAssignedToWaiter> findByCafeTableId(Long id);
}
