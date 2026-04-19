package com.ds.sqs.taxation.security.service;

import com.ds.sqs.taxation.security.dto.CompanyDto;

import java.util.List;

public interface CompanyService {
    CompanyDto create(CompanyDto dto);

    CompanyDto getById(Long id);

    List<CompanyDto> findAll();
}
