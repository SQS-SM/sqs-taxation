package com.ds.sqs.taxation.payroll.repository;


import com.ds.sqs.taxation.payroll.entity.Uif;import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface UifRepository extends JpaRepository<Uif, Integer> {

    @Query("""
                SELECT u
                FROM Uif u
                WHERE u.active = true
                  AND :date BETWEEN u.effectiveFrom AND u.effectiveTo
                ORDER BY u.effectiveFrom DESC
            """)
    Optional<Uif> findActiveUIF(@Param("date") LocalDate date);

    default Optional<Uif> findCurrentUIF() {
        return findActiveUIF(LocalDate.now());
    }
}