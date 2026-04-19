package com.ds.sqs.taxation.security.adapter.impl;

import com.ds.sqs.taxation.common.mapper.AddressMapper;
import com.ds.sqs.taxation.security.dto.OrganisationDto;
import com.ds.sqs.taxation.security.adapter.OrganisationPersistencePort;
import com.ds.sqs.taxation.security.mapper.OrganisationMapper;
import com.ds.sqs.taxation.security.repository.OrganisationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OrganisationPersistenceAdapter implements OrganisationPersistencePort {

    private final OrganisationRepository repository;
    private final OrganisationMapper mapper;

    @Override
    public OrganisationDto save(OrganisationDto organisation) {
        var entity = mapper.toEntity(organisation);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public Optional<OrganisationDto> findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    public Optional<OrganisationDto> findByGuid(UUID guid) {
        return repository.findByGuid(guid)
                .map(mapper::toDto);
    }

    @Override
    public List<OrganisationDto> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDto).toList();
    }
}