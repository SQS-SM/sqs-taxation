package com.ds.sqs.taxation.common.service.impl;

import com.ds.sqs.taxation.common.dto.BankDetailsDto;
import com.ds.sqs.taxation.common.entity.BankDetails;
import com.ds.sqs.taxation.common.mapper.BankDetailsMapper;
import com.ds.sqs.taxation.common.repository.BankDetailsRepository;
import com.ds.sqs.taxation.common.service.BankDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BankDetailsServiceImpl implements BankDetailsService {

    private final BankDetailsRepository bankDetailsRepository;
    private final BankDetailsMapper mapper;

    @Override
    public BankDetailsDto create(BankDetailsDto dto) {
        BankDetails entity = mapper.toEntity(dto);
        return mapper.toDto(bankDetailsRepository.save(entity));
    }

    @Override
    public BankDetailsDto getById(Long id) {
        return bankDetailsRepository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("BankDetails not found"));
    }

    @Override
    public List<BankDetailsDto> findAll() {
        return bankDetailsRepository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public BankDetailsDto update(Long id, BankDetailsDto dto) {
        BankDetails existing = bankDetailsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("BankDetails not found"));
        BankDetails updated = mapper.toEntity(dto);
        updated.setId(id);
        return mapper.toDto(bankDetailsRepository.save(updated));
    }

    @Override
    public void delete(Long id) {
        if (!bankDetailsRepository.existsById(id)) {
            throw new RuntimeException("BankDetails not found");
        }
        bankDetailsRepository.deleteById(id);
    }
}