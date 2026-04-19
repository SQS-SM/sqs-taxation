package com.ds.sqs.taxation.common.adapter;

import com.ds.sqs.taxation.common.dto.ProvinceDto;

import java.util.List;
import java.util.Optional;

public interface ProvinceRepositoryPort {

    ProvinceDto save(ProvinceDto provinceDto);

    Optional<ProvinceDto> findById(Long id);

    List<ProvinceDto> findAll();

    List<ProvinceDto> findByCountryId(Long countryId);

    void deleteById(Long id);
}