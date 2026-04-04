package com.ds.sqs.taxation.payroll.adapter.impl;

import com.ds.sqs.taxation.payroll.adapter.AllowanceTypePersistencePort;
import com.ds.sqs.taxation.payroll.dto.AllowanceTypeDto;
import com.ds.sqs.taxation.payroll.entity.AllowanceType;
import com.ds.sqs.taxation.payroll.enums.AllowanceCode;
import com.ds.sqs.taxation.payroll.mapper.AllowanceTypeMapper;
import com.ds.sqs.taxation.payroll.repository.AllowanceTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AllowanceTypePersistenceAdapter
        implements AllowanceTypePersistencePort {

    private final AllowanceTypeRepository repository;
    private final AllowanceTypeMapper mapper;

    @Override
    @Caching(evict = {
            @CacheEvict(cacheNames = "allowanceTypeById", allEntries = true),
            @CacheEvict(cacheNames = "allowanceTypeByCode", allEntries = true),
            @CacheEvict(cacheNames = "allowanceTypeAll", allEntries = true)
    })
    public AllowanceTypeDto save(AllowanceType entity) {
        return mapper.toDto(repository.save(entity));
    }

    @Override
    @Cacheable(cacheNames = "allowanceTypeById", key = "#id")
    public Optional<AllowanceTypeDto> findById(Integer id) {
        return repository.findById(id).map(mapper::toDto);
    }

    @Override
    @Cacheable(cacheNames = "allowanceTypeByCode", key = "#code.name()")
    public Optional<AllowanceTypeDto> findByCode(AllowanceCode code) {
        return repository.findByAllowanceCode(code).map(mapper::toDto);
    }

    @Override
    @Cacheable(cacheNames = "allowanceTypeAll", key = "'all'")
    public List<AllowanceTypeDto> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }
}
