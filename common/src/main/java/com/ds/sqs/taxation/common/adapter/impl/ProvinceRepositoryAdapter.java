package com.ds.sqs.taxation.common.adapter.impl;

import com.ds.sqs.taxation.common.adapter.ProvinceRepositoryPort;
import com.ds.sqs.taxation.common.dto.ProvinceDto;
import com.ds.sqs.taxation.common.mapper.CountryMapper;
import com.ds.sqs.taxation.common.mapper.ProvinceMapper;
import com.ds.sqs.taxation.common.repository.ProvinceRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProvinceRepositoryAdapter implements ProvinceRepositoryPort {
    private final ProvinceRepository repository;
    private final ProvinceMapper mapper;

    @Override
    public ProvinceDto save(ProvinceDto provinceDto) {
        var entity = mapper.toEntity(provinceDto);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public Optional<ProvinceDto> findById(Long id) {
        return repository.findById(id).map(mapper::toDto);
    }

    @Override
    public List<ProvinceDto> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDto).toList();
    }

    @Override
    public List<ProvinceDto> findByCountryId(Long countryId) {
        return repository.findByCountry_Id(countryId).stream()
                .map(mapper::toDto).toList();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}