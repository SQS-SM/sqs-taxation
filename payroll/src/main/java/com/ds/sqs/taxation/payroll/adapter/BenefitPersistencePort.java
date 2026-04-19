package com.ds.sqs.taxation.payroll.adapter;

import com.ds.sqs.taxation.payroll.dto.BenefitDto;
import com.ds.sqs.taxation.payroll.entity.Benefit;

import java.util.List;
import java.util.Optional;

public interface BenefitPersistencePort {

    BenefitDto save(Benefit entity);

    Optional<BenefitDto> findById(Integer id);

    List<BenefitDto> findByTaxYear(String taxYear);

    List<BenefitDto> findAll();
}
