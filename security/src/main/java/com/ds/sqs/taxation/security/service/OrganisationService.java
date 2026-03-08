package com.ds.sqs.taxation.security.service;

import com.ds.sqs.taxation.security.dto.OrganisationDto;

import java.util.List;

public interface OrganisationService {

    OrganisationDto create(OrganisationDto dto);

    OrganisationDto getById(Long id);

    List<OrganisationDto> findAll();

    OrganisationDto update(Long id, OrganisationDto dto);

    void delete(Long id);
}
