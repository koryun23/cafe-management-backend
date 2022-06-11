package com.cafe.repository;

import com.cafe.entity.table.CafeTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CafeTableRepository extends JpaRepository<CafeTable, Long> {
}
