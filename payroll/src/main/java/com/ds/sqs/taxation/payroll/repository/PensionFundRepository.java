package com.ds.sqs.taxation.payroll.repository;


import com.ds.sqs.taxation.payroll.entity.PensionFund;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PensionFundRepository extends JpaRepository<PensionFund, Integer> {

    // Find pension fund by tax year
    Optional<PensionFund> findByTaxYear(String taxYear);

}
