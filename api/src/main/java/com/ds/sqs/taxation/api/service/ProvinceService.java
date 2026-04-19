package com.ds.sqs.taxation.api.service;

import com.ds.sqs.taxation.common.adapter.ProvinceRepositoryPort;
import com.ds.sqs.taxation.common.dto.ProvinceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProvinceService {
    private final ProvinceRepositoryPort repository;

    public List<ProvinceDto> findByCountry(Long countryId) {
        return repository.findByCountryId(countryId);
    }

    public Optional<ProvinceDto> findById(Long id) {
        return repository.findById(id);
    }

    public List<ProvinceDto> findAll() {
        return repository.findAll();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
