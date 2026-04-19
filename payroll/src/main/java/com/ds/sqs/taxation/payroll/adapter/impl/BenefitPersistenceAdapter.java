package com.ds.sqs.taxation.payroll.adapter.impl;

import com.ds.sqs.taxation.payroll.adapter.BenefitPersistencePort;
import com.ds.sqs.taxation.payroll.dto.BenefitDto;
import com.ds.sqs.taxation.payroll.entity.Benefit;
import com.ds.sqs.taxation.payroll.mapper.BenefitMapper;
import com.ds.sqs.taxation.payroll.repository.BenefitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BenefitPersistenceAdapter implements BenefitPersistencePort {

    private final BenefitRepository repository;
    private final BenefitMapper mapper;

    @Override
    public BenefitDto save(Benefit entity) {
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public Optional<BenefitDto> findById(Integer id) {
        return repository.findById(id).map(mapper::toDto);
    }

    @Override
    public List<BenefitDto> findByTaxYear(String taxYear) {
        return repository.findByTaxYear(taxYear)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public List<BenefitDto> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }
}
