package com.ds.sqs.taxation.payroll.repository;

import com.ds.sqs.taxation.payroll.entity.Benefit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BenefitRepository
        extends JpaRepository<Benefit, Integer> {

    List<Benefit> findByTaxYear(String taxYear);
}
