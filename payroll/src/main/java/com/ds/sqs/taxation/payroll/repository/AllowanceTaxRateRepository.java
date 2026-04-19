package com.ds.sqs.taxation.payroll.repository;

import com.ds.sqs.taxation.payroll.entity.AllowanceTaxRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AllowanceTaxRateRepository
        extends JpaRepository<AllowanceTaxRate, Long> {

    @Query("""
    SELECT r
    FROM AllowanceTaxRate r
    WHERE r.allowanceType.allowanceCode = :allowanceCode
      AND r.taxYear = :taxYear
      AND r.active = true
""")
    Optional<AllowanceTaxRate> findByAllowanceTypeCodeAndTaxYear(
            @Param("allowanceCode") com.ds.sqs.taxation.payroll.enums.AllowanceCode allowanceCode,
            @Param("taxYear")  String taxYear
    );
}
