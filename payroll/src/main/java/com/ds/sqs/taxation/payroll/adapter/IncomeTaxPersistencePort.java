package com.ds.sqs.taxation.payroll.adapter;

import com.ds.sqs.taxation.payroll.dto.IncomeTaxDto;
import com.ds.sqs.taxation.payroll.entity.IncomeTax;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface IncomeTaxPersistencePort {

    IncomeTaxDto save(IncomeTax entity);

    Optional<IncomeTaxDto> findById(Long id);

    Optional<IncomeTaxDto> findByIncomeRange(BigDecimal minimumIncome, BigDecimal maximumIncome);

    List<IncomeTaxDto> findByYear(String year);

    List<IncomeTaxDto> findAll();
}
