package com.ds.sqs.taxation.common.adapter;

import com.ds.sqs.taxation.common.dto.CountryDto;
import com.ds.sqs.taxation.common.entity.Country;

import java.util.List;
import java.util.Optional;

public interface CountryRepositoryPort {
    CountryDto save(CountryDto countryDto);
    Optional<CountryDto> findById(Long id);
    List<CountryDto> findAll();
}
