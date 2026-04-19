package com.ds.sqs.taxation.common.adapter;

import com.ds.sqs.taxation.common.dto.AddressDto;
import com.ds.sqs.taxation.common.entity.Address;

import java.util.List;
import java.util.Optional;


public interface AddressRepositoryPort {

    AddressDto save(AddressDto address);

    Optional<AddressDto> findById(Long id);

    List<AddressDto> findAll();

    List<AddressDto> findByCity(String city);

    void deleteById(Long id);
}
