package com.ds.sqs.taxation.payroll.adapter.impl;

import com.ds.sqs.taxation.payroll.adapter.BenefitPersistencePort;
import com.ds.sqs.taxation.payroll.dto.BenefitDto;
import com.ds.sqs.taxation.payroll.entity.Benefit;
import com.ds.sqs.taxation.payroll.mapper.BenefitMapper;
import com.ds.sqs.taxation.payroll.repository.BenefitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BenefitPersistenceAdapter implements BenefitPersistencePort {

    private final BenefitRepository repository;
    private final BenefitMapper mapper;

    @Override
    @Caching(evict = {
            @CacheEvict(cacheNames = "benefitById", allEntries = true),
            @CacheEvict(cacheNames = "benefitByTaxYear", allEntries = true),
            @CacheEvict(cacheNames = "benefitAll", allEntries = true)
    })
    public BenefitDto save(Benefit entity) {
        return mapper.toDto(repository.save(entity));
    }

    @Override
    @Cacheable(cacheNames = "benefitById", key = "#id")
    public Optional<BenefitDto> findById(Integer id) {
        return repository.findById(id).map(mapper::toDto);
    }

    @Override
    @Cacheable(cacheNames = "benefitByTaxYear", key = "#taxYear")
    public List<BenefitDto> findByTaxYear(String taxYear) {
        return repository.findByTaxYear(taxYear)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    @Cacheable(cacheNames = "benefitAll", key = "'all'")
    public List<BenefitDto> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }
}
