package com.ds.sqs.taxation.payroll.adapter;

import com.ds.sqs.taxation.payroll.dto.MedicalSchemeTaxCreditRateDto;
import com.ds.sqs.taxation.payroll.entity.MedicalSchemeTaxCreditRate;
import com.ds.sqs.taxation.payroll.enums.MedicalSchemeTaxCreditType;

import java.util.List;
import java.util.Optional;

public interface MedicalSchemeTaxCreditRatePersistencePort {

    MedicalSchemeTaxCreditRateDto save(MedicalSchemeTaxCreditRate entity);

    Optional<MedicalSchemeTaxCreditRateDto> findById(Long id);

    Optional<MedicalSchemeTaxCreditRateDto> findByTaxYearAndCreditType(Integer taxYear,
                                                                        MedicalSchemeTaxCreditType creditType);

    List<MedicalSchemeTaxCreditRateDto> findAll();
}
