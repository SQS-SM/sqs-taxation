package com.ds.sqs.taxation.security.adapter;

import com.ds.sqs.taxation.security.dto.CompanyDto;
import com.ds.sqs.taxation.security.entity.Company;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CompanyPersistencePort {

    CompanyDto save(Company company);

    Optional<CompanyDto> findById(Long id);

    Optional<CompanyDto> findByGuid(UUID guid);

    List<CompanyDto> findAll();
}