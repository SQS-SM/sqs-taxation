package com.ds.sqs.taxation.payroll.adapter.impl;

import com.ds.sqs.taxation.payroll.adapter.TaxThresholdPersistencePort;
import com.ds.sqs.taxation.payroll.dto.TaxThresholdDto;
import com.ds.sqs.taxation.payroll.entity.TaxThreshold;
import com.ds.sqs.taxation.payroll.mapper.TaxThresholdMapper;
import com.ds.sqs.taxation.payroll.repository.TaxThresholdRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TaxThresholdPersistenceAdapter implements TaxThresholdPersistencePort {

    private final TaxThresholdRepository repository;
    private final TaxThresholdMapper mapper;

    @Override
    public TaxThresholdDto save(TaxThreshold entity) {
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public Optional<TaxThresholdDto> findById(Integer id) {
        return repository.findById(id).map(mapper::toDto);
    }

    @Override
    public List<TaxThresholdDto> findByYear(String year) {
        return repository.findByYear(year)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public List<TaxThresholdDto> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }
}
