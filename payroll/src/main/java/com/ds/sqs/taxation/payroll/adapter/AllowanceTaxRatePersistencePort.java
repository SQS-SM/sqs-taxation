package com.ds.sqs.taxation.payroll.adapter;

import com.ds.sqs.taxation.payroll.dto.AllowanceTaxRateDto;
import com.ds.sqs.taxation.payroll.entity.AllowanceTaxRate;
import com.ds.sqs.taxation.payroll.enums.AllowanceCode;

import java.util.List;
import java.util.Optional;

public interface AllowanceTaxRatePersistencePort {

    AllowanceTaxRateDto save(AllowanceTaxRate entity);

    Optional<AllowanceTaxRateDto> findById(Long id);

    Optional<AllowanceTaxRateDto> findByAllowanceTypeCodeAndTaxYear(AllowanceCode allowanceCode, String taxYear);

    List<AllowanceTaxRateDto> findAll();
}
