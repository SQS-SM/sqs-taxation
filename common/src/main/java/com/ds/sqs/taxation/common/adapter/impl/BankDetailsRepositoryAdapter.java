package com.ds.sqs.taxation.common.adapter.impl;

import com.ds.sqs.taxation.common.dto.BankDetailsDto;
import com.ds.sqs.taxation.common.adapter.BankDetailsRepositoryPort;
import com.ds.sqs.taxation.common.mapper.BankDetailsMapper;
import com.ds.sqs.taxation.common.repository.BankDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BankDetailsRepositoryAdapter implements BankDetailsRepositoryPort {

    private final BankDetailsRepository repository;
    private final BankDetailsMapper mapper;

    @Override
    public BankDetailsDto save(BankDetailsDto bankDetailsDto) {
        var entity = mapper.toEntity(bankDetailsDto);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public Optional<BankDetailsDto> findById(Long id) {
        return repository.findById(id).map(mapper::toDto);
    }

    @Override
    public List<BankDetailsDto> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDto).toList();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
