package com.ds.sqs.taxation.common.service;

import com.ds.sqs.taxation.common.dto.AddressDto;

import java.util.List;

public interface AddressService {

    AddressDto create(AddressDto dto);

    AddressDto getById(Long id);

    List<AddressDto> findAll();

    AddressDto update(Long id, AddressDto dto);

    void delete(Long id);
}