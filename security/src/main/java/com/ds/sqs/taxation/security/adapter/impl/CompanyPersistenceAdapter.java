package com.ds.sqs.taxation.security.adapter.impl;

import com.ds.sqs.taxation.security.dto.CompanyDto;
import com.ds.sqs.taxation.security.adapter.CompanyPersistencePort;
import com.ds.sqs.taxation.security.entity.Company;
import com.ds.sqs.taxation.security.mapper.CompanyMapper;
import com.ds.sqs.taxation.security.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CompanyPersistenceAdapter implements CompanyPersistencePort {

    private final CompanyRepository repository;
    private final CompanyMapper mapper;

    @Override
    public CompanyDto save(Company company) {
        return mapper.toDto(repository.save(company));
    }

    @Override
    public Optional<CompanyDto> findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    public Optional<CompanyDto> findByGuid(UUID guid) {
        return repository.findByGuid(guid)
                .map(mapper::toDto);
    }

    @Override
    public List<CompanyDto> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDto).toList();
    }
}
