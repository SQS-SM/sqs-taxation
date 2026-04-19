package com.ds.sqs.taxation.payroll.repository;


import com.ds.sqs.taxation.payroll.entity.MedicalSchemeTaxCreditRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MedicalSchemeTaxCreditRateRepository extends JpaRepository<MedicalSchemeTaxCreditRate, Long> {
    @Query(
            value = """
                      select *
                      from sars.medical_scheme_tax_credit_rate
                      where tax_year = :taxYear
                        and credit_type_code = CAST(:creditType AS sars.medical_scheme_tax_credit_enum)
                    """,
            nativeQuery = true
    )
    Optional<MedicalSchemeTaxCreditRate> findByTaxYearAndCreditType(
            @Param("taxYear")  Integer taxYear,
            @Param("creditType")  String creditType
    );
}