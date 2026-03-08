package com.ds.sqs.taxation.common.adapter.impl;

import com.ds.sqs.taxation.common.adapter.ProvinceRepositoryPort;
import com.ds.sqs.taxation.common.dto.ProvinceDto;
import com.ds.sqs.taxation.common.mapper.ProvinceMapper;
import com.ds.sqs.taxation.common.repository.ProvinceRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProvinceRepositoryAdapter implements ProvinceRepositoryPort {
    private final ProvinceRepository repository;
    private final ProvinceMapper mapper;

    @Override
    @Caching(evict = {
            @CacheEvict(cacheNames = "provinceById", allEntries = true),
            @CacheEvict(cacheNames = "provinceByCountryId", allEntries = true),
            @CacheEvict(cacheNames = "provinceAll", allEntries = true)
    })
    public ProvinceDto save(ProvinceDto provinceDto) {
        var entity = mapper.toEntity(provinceDto);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    @Cacheable(cacheNames = "provinceById", key = "#id")
    public Optional<ProvinceDto> findById(Long id) {
        return repository.findById(id).map(mapper::toDto);
    }

    @Override
    @Cacheable(cacheNames = "provinceAll", key = "'all'")
    public List<ProvinceDto> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDto).toList();
    }

    @Override
    @Cacheable(cacheNames = "provinceByCountryId", key = "#countryId")
    public List<ProvinceDto> findByCountryId(Long countryId) {
        return repository.findByCountry_Id(countryId).stream()
                .map(mapper::toDto).toList();
    }

    @Override
    @Caching(evict = {
            @CacheEvict(cacheNames = "provinceById", key = "#id"),
            @CacheEvict(cacheNames = "provinceByCountryId", allEntries = true),
            @CacheEvict(cacheNames = "provinceAll", allEntries = true)
    })
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
