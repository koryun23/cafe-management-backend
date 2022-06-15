package com.cafe.repository;

import com.cafe.entity.table.CafeTableAssignedToWaiter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CafeTableAssignedToWaiterRepository extends JpaRepository<CafeTableAssignedToWaiter, Long> {
    List<CafeTableAssignedToWaiter> findAllByWaiterId(Long id);

    Optional<CafeTableAssignedToWaiter> findByCafeTableId(Long id);
}
