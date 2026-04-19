package com.ds.sqs.taxation.payroll.adapter.impl;

import com.ds.sqs.taxation.payroll.adapter.MedicalSchemeTaxCreditRatePersistencePort;
import com.ds.sqs.taxation.payroll.dto.MedicalSchemeTaxCreditRateDto;
import com.ds.sqs.taxation.payroll.entity.MedicalSchemeTaxCreditRate;
import com.ds.sqs.taxation.payroll.enums.MedicalSchemeTaxCreditType;
import com.ds.sqs.taxation.payroll.mapper.MedicalSchemeTaxCreditRateMapper;
import com.ds.sqs.taxation.payroll.repository.MedicalSchemeTaxCreditRateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MedicalSchemeTaxCreditRatePersistenceAdapter implements MedicalSchemeTaxCreditRatePersistencePort {

    private final MedicalSchemeTaxCreditRateRepository repository;
    private final MedicalSchemeTaxCreditRateMapper mapper;

    @Override
    public MedicalSchemeTaxCreditRateDto save(MedicalSchemeTaxCreditRate entity) {
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public Optional<MedicalSchemeTaxCreditRateDto> findById(Long id) {
        return repository.findById(id).map(mapper::toDto);
    }

    @Override
    public Optional<MedicalSchemeTaxCreditRateDto> findByTaxYearAndCreditType(Integer taxYear,
                                                                               MedicalSchemeTaxCreditType creditType) {
        return repository.findByTaxYearAndCreditType(taxYear, creditType.name()).map(mapper::toDto);
    }

    @Override
    public List<MedicalSchemeTaxCreditRateDto> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }
}
