package com.ds.sqs.taxation.common.adapter.impl;


import com.ds.sqs.taxation.common.dto.AddressDto;
import com.ds.sqs.taxation.common.adapter.AddressRepositoryPort;
import com.ds.sqs.taxation.common.mapper.AddressMapper;
import com.ds.sqs.taxation.common.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AddressRepositoryAdapter implements AddressRepositoryPort {
    private final AddressRepository repository;
    private final AddressMapper mapper;

    @Override
    public AddressDto save(AddressDto addressDto) {
        var entity = mapper.toEntity(addressDto);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public Optional<AddressDto> findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    public List<AddressDto> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDto).toList();
    }

    @Override
    public List<AddressDto> findByCity(String city) {
        return repository.findByCityIgnoreCase(city).stream()
                .map(mapper::toDto).toList();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}