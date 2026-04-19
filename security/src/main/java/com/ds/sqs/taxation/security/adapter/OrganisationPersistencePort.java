package com.ds.sqs.taxation.security.adapter;

import com.ds.sqs.taxation.security.dto.OrganisationDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrganisationPersistencePort {

    OrganisationDto save(OrganisationDto organisation);

    Optional<OrganisationDto> findById(Long id);

    Optional<OrganisationDto> findByGuid(UUID guid);

    List<OrganisationDto> findAll();
}
