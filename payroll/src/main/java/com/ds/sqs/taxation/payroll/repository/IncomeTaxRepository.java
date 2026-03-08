package com.ds.sqs.taxation.payroll.repository;

import com.ds.sqs.taxation.payroll.entity.IncomeTax;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface IncomeTaxRepository extends JpaRepository<IncomeTax, Long> {

    Optional<IncomeTax> findByMinimumIncomeLessThanEqualAndMaximumIncomeGreaterThanEqual(
            BigDecimal minimumIncome,
            BigDecimal maximumIncome
    );

    List<IncomeTax> findByYear(String year);
}
