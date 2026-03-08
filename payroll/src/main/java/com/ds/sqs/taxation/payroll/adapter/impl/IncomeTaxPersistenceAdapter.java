package com.ds.sqs.taxation.payroll.adapter.impl;

import com.ds.sqs.taxation.payroll.adapter.IncomeTaxPersistencePort;
import com.ds.sqs.taxation.payroll.dto.IncomeTaxDto;
import com.ds.sqs.taxation.payroll.entity.IncomeTax;
import com.ds.sqs.taxation.payroll.mapper.IncomeTaxMapper;
import com.ds.sqs.taxation.payroll.repository.IncomeTaxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class IncomeTaxPersistenceAdapter implements IncomeTaxPersistencePort {

    private final IncomeTaxRepository repository;
    private final IncomeTaxMapper mapper;

    @Override
    @Caching(evict = {
            @CacheEvict(cacheNames = "incomeTaxById", allEntries = true),
            @CacheEvict(cacheNames = "incomeTaxByRange", allEntries = true),
            @CacheEvict(cacheNames = "incomeTaxByYear", allEntries = true),
            @CacheEvict(cacheNames = "incomeTaxAll", allEntries = true)
    })
    public IncomeTaxDto save(IncomeTax entity) {
        return mapper.toDto(repository.save(entity));
    }

    @Override
    @Cacheable(cacheNames = "incomeTaxById", key = "#id")
    public Optional<IncomeTaxDto> findById(Long id) {
        return repository.findById(id).map(mapper::toDto);
    }

    @Override
    @Cacheable(cacheNames = "incomeTaxByRange", key = "#minimumIncome.toPlainString().concat(':').concat(#maximumIncome.toPlainString())")
    public Optional<IncomeTaxDto> findByIncomeRange(BigDecimal minimumIncome, BigDecimal maximumIncome) {
        return repository.findByMinimumIncomeLessThanEqualAndMaximumIncomeGreaterThanEqual(minimumIncome, maximumIncome)
                .map(mapper::toDto);
    }

    @Override
    @Cacheable(cacheNames = "incomeTaxByYear", key = "#year")
    public List<IncomeTaxDto> findByYear(String year) {
        return repository.findByYear(year)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    @Cacheable(cacheNames = "incomeTaxAll", key = "'all'")
    public List<IncomeTaxDto> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }
}
