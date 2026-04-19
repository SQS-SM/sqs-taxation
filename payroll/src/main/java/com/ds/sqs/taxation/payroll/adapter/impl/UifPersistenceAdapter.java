package com.ds.sqs.taxation.payroll.adapter.impl;

import com.ds.sqs.taxation.payroll.adapter.UifPersistencePort;
import com.ds.sqs.taxation.payroll.dto.UifDto;
import com.ds.sqs.taxation.payroll.entity.Uif;
import com.ds.sqs.taxation.payroll.mapper.UifMapper;
import com.ds.sqs.taxation.payroll.repository.UifRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UifPersistenceAdapter implements UifPersistencePort {

    private final UifRepository repository;
    private final UifMapper mapper;

    @Override
    public UifDto save(Uif entity) {
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public Optional<UifDto> findById(Integer id) {
        return repository.findById(id).map(mapper::toDto);
    }

    @Override
    public Optional<UifDto> findEffective(LocalDate date) {
        return repository.findActiveUIF(date).map(mapper::toDto);
    }

    @Override
    public Optional<UifDto> findCurrent() {
        return repository.findCurrentUIF().map(mapper::toDto);
    }

    @Override
    public List<UifDto> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }
}
