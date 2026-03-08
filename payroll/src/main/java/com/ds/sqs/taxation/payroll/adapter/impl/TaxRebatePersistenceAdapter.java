package com.ds.sqs.taxation.payroll.adapter.impl;

import com.ds.sqs.taxation.payroll.adapter.TaxRebatePersistencePort;
import com.ds.sqs.taxation.payroll.dto.TaxRebateDto;
import com.ds.sqs.taxation.payroll.entity.TaxRebate;
import com.ds.sqs.taxation.payroll.mapper.TaxRebateMapper;
import com.ds.sqs.taxation.payroll.repository.TaxRebateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TaxRebatePersistenceAdapter implements TaxRebatePersistencePort {

    private final TaxRebateRepository repository;
    private final TaxRebateMapper mapper;

    @Override
    @Caching(evict = {
            @CacheEvict(cacheNames = "taxRebateById", allEntries = true),
            @CacheEvict(cacheNames = "taxRebateByYear", allEntries = true),
            @CacheEvict(cacheNames = "taxRebateByDescriptionAndYear", allEntries = true),
            @CacheEvict(cacheNames = "taxRebateAll", allEntries = true)
    })
    public TaxRebateDto save(TaxRebate entity) {
        return mapper.toDto(repository.save(entity));
    }

    @Override
    @Cacheable(cacheNames = "taxRebateById", key = "#id")
    public Optional<TaxRebateDto> findById(Integer id) {
        return repository.findById(id).map(mapper::toDto);
    }

    @Override
    @Cacheable(cacheNames = "taxRebateByYear", key = "#year")
    public List<TaxRebateDto> findByYear(String year) {
        return repository.findByYear(year)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    @Cacheable(cacheNames = "taxRebateByDescriptionAndYear", key = "#rebateDescription.concat(':').concat(#year)")
    public Optional<TaxRebateDto> findByRebateDescriptionAndYear(String rebateDescription, String year) {
        return repository.findByRebateDescriptionAndYear(rebateDescription, year).map(mapper::toDto);
    }

    @Override
    @Cacheable(cacheNames = "taxRebateAll", key = "'all'")
    public List<TaxRebateDto> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }
}
