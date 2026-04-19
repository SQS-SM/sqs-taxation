package com.ds.sqs.taxation.payroll.adapter.impl;

import com.ds.sqs.taxation.payroll.adapter.PensionFundPersistencePort;
import com.ds.sqs.taxation.payroll.dto.PensionFundDto;
import com.ds.sqs.taxation.payroll.entity.PensionFund;
import com.ds.sqs.taxation.payroll.mapper.PensionFundMapper;
import com.ds.sqs.taxation.payroll.repository.PensionFundRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PensionFundPersistenceAdapter implements PensionFundPersistencePort {

    private final PensionFundRepository repository;
    private final PensionFundMapper mapper;

    @Override
    public PensionFundDto save(PensionFund entity) {
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public Optional<PensionFundDto> findById(Integer id) {
        return repository.findById(id).map(mapper::toDto);
    }

    @Override
    public Optional<PensionFundDto> findByTaxYear(String taxYear) {
        return repository.findByTaxYear(taxYear).map(mapper::toDto);
    }

    @Override
    public List<PensionFundDto> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }
}
