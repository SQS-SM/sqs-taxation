package com.ds.sqs.taxation.common.adapter.impl;

import com.ds.sqs.taxation.common.adapter.CountryRepositoryPort;
import com.ds.sqs.taxation.common.dto.CountryDto;
import com.ds.sqs.taxation.common.mapper.CountryMapper;
import com.ds.sqs.taxation.common.repository.CountryRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CountryAdapter implements CountryRepositoryPort {

    private final CountryRepository jpaRepository;
    private final CountryMapper mapper;

    public CountryAdapter(CountryRepository jpaRepository, CountryMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    @Caching(evict = {
            @CacheEvict(cacheNames = "countryById", allEntries = true),
            @CacheEvict(cacheNames = "countryAll", allEntries = true)
    })
    public CountryDto save(CountryDto countryDto) {
        var entity = mapper.toEntity(countryDto);
        return mapper.toDto(jpaRepository.save(entity));
    }

    @Override
    @Cacheable(cacheNames = "countryById", key = "#id")
    public Optional<CountryDto> findById(Long id) {
        return jpaRepository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    @Cacheable(cacheNames = "countryAll", key = "'all'")
    public List<CountryDto> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDto).toList();
    }
}
