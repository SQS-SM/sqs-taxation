package com.ds.sqs.taxation.security.service;

import com.ds.sqs.taxation.security.dto.CompanyDto;

import java.util.List;

public interface CompanyService {
    CompanyDto create(CompanyDto dto);

    CompanyDto getById(Long id);

    List<CompanyDto> findAll();

    List<CompanyDto> findByOrganisationId(Long organisationId);

    CompanyDto update(Long id, CompanyDto dto);

    void delete(Long id);
}
