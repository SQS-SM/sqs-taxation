package com.ds.sqs.taxation.payroll.adapter.impl;

import com.ds.sqs.taxation.payroll.adapter.AllowanceTaxRatePersistencePort;
import com.ds.sqs.taxation.payroll.dto.AllowanceTaxRateDto;
import com.ds.sqs.taxation.payroll.entity.AllowanceTaxRate;
import com.ds.sqs.taxation.payroll.enums.AllowanceCode;
import com.ds.sqs.taxation.payroll.mapper.AllowanceTaxRateMapper;
import com.ds.sqs.taxation.payroll.repository.AllowanceTaxRateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AllowanceTaxRatePersistenceAdapter implements AllowanceTaxRatePersistencePort {

    private final AllowanceTaxRateRepository repository;
    private final AllowanceTaxRateMapper mapper;

    @Override
    @Caching(evict = {
            @CacheEvict(cacheNames = "allowanceTaxRateById", allEntries = true),
            @CacheEvict(cacheNames = "allowanceTaxRateByCodeAndTaxYear", allEntries = true),
            @CacheEvict(cacheNames = "allowanceTaxRateAll", allEntries = true)
    })
    public AllowanceTaxRateDto save(AllowanceTaxRate entity) {
        return mapper.toDto(repository.save(entity));
    }

    @Override
    @Cacheable(cacheNames = "allowanceTaxRateById", key = "#id")
    public Optional<AllowanceTaxRateDto> findById(Long id) {
        return repository.findById(id).map(mapper::toDto);
    }

    @Override
    @Cacheable(cacheNames = "allowanceTaxRateByCodeAndTaxYear", key = "#allowanceCode.name().concat(':').concat(#taxYear)")
    public Optional<AllowanceTaxRateDto> findByAllowanceTypeCodeAndTaxYear(AllowanceCode allowanceCode,
                                                                            String taxYear) {
        return repository.findByAllowanceTypeCodeAndTaxYear(allowanceCode, taxYear).map(mapper::toDto);
    }

    @Override
    @Cacheable(cacheNames = "allowanceTaxRateAll", key = "'all'")
    public List<AllowanceTaxRateDto> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }
}
