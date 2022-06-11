package com.cafe.repository;

import com.cafe.entity.table.CafeTableAssignedToWaiter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CafeTableAssignedToWaiterRepository extends JpaRepository<CafeTableAssignedToWaiter, Long> {
    List<CafeTableAssignedToWaiter> findAllByWaiterId(Long id);
}
