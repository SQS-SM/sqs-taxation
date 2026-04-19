package com.ds.sqs.taxation.payroll.adapter;

import com.ds.sqs.taxation.payroll.dto.PensionFundDto;
import com.ds.sqs.taxation.payroll.entity.PensionFund;

import java.util.List;
import java.util.Optional;

public interface PensionFundPersistencePort {

    PensionFundDto save(PensionFund entity);

    Optional<PensionFundDto> findById(Integer id);

    Optional<PensionFundDto> findByTaxYear(String taxYear);

    List<PensionFundDto> findAll();
}
