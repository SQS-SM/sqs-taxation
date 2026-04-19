package com.ds.sqs.taxation.common.service.impl;

import com.ds.sqs.taxation.common.dto.AddressDto;
import com.ds.sqs.taxation.common.entity.Address;
import com.ds.sqs.taxation.common.mapper.AddressMapper;
import com.ds.sqs.taxation.common.repository.AddressRepository;
import com.ds.sqs.taxation.common.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper mapper;

    @Override
    public AddressDto create(AddressDto dto) {
        Address entity = mapper.toEntity(dto);
        return mapper.toDto(addressRepository.save(entity));
    }

    @Override
    public AddressDto getById(Long id) {
        return addressRepository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("Address not found"));
    }

    @Override
    public List<AddressDto> findAll() {
        return addressRepository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public AddressDto update(Long id, AddressDto dto) {
        Address existing = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found"));
        Address updated = mapper.toEntity(dto);
        updated.setId(id);
        return mapper.toDto(addressRepository.save(updated));
    }

    @Override
    public void delete(Long id) {
        if (!addressRepository.existsById(id)) {
            throw new RuntimeException("Address not found");
        }
        addressRepository.deleteById(id);
    }
}