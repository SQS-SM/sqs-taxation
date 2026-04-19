package com.ds.sqs.taxation.api.service;

import com.ds.sqs.taxation.common.adapter.CountryRepositoryPort;
import com.ds.sqs.taxation.common.dto.CountryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CountryService {
    private final CountryRepositoryPort repository;

    public Optional<CountryDto> findById(Long id) {
        return repository.findById(id);
    }

    public List<CountryDto> findAll() {
        return repository.findAll();
    }
}
