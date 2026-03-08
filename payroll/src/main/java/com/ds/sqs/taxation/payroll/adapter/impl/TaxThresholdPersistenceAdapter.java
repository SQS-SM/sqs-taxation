package com.ds.sqs.taxation.payroll.adapter.impl;

import com.ds.sqs.taxation.payroll.adapter.TaxThresholdPersistencePort;
import com.ds.sqs.taxation.payroll.dto.TaxThresholdDto;
import com.ds.sqs.taxation.payroll.entity.TaxThreshold;
import com.ds.sqs.taxation.payroll.mapper.TaxThresholdMapper;
import com.ds.sqs.taxation.payroll.repository.TaxThresholdRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TaxThresholdPersistenceAdapter implements TaxThresholdPersistencePort {

    private final TaxThresholdRepository repository;
    private final TaxThresholdMapper mapper;

    @Override
    @Caching(evict = {
            @CacheEvict(cacheNames = "taxThresholdById", allEntries = true),
            @CacheEvict(cacheNames = "taxThresholdByYear", allEntries = true),
            @CacheEvict(cacheNames = "taxThresholdAll", allEntries = true)
    })
    public TaxThresholdDto save(TaxThreshold entity) {
        return mapper.toDto(repository.save(entity));
    }

    @Override
    @Cacheable(cacheNames = "taxThresholdById", key = "#id")
    public Optional<TaxThresholdDto> findById(Integer id) {
        return repository.findById(id).map(mapper::toDto);
    }

    @Override
    @Cacheable(cacheNames = "taxThresholdByYear", key = "#year")
    public List<TaxThresholdDto> findByYear(String year) {
        return repository.findByYear(year)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    @Cacheable(cacheNames = "taxThresholdAll", key = "'all'")
    public List<TaxThresholdDto> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }
}
