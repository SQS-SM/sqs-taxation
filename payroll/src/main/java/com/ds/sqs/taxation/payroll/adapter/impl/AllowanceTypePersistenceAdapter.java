package com.ds.sqs.taxation.payroll.adapter.impl;

import com.ds.sqs.taxation.payroll.adapter.AllowanceTypePersistencePort;
import com.ds.sqs.taxation.payroll.dto.AllowanceTypeDto;
import com.ds.sqs.taxation.payroll.entity.AllowanceType;
import com.ds.sqs.taxation.payroll.enums.AllowanceCode;
import com.ds.sqs.taxation.payroll.mapper.AllowanceTypeMapper;
import com.ds.sqs.taxation.payroll.repository.AllowanceTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AllowanceTypePersistenceAdapter
        implements AllowanceTypePersistencePort {

    private final AllowanceTypeRepository repository;
    private final AllowanceTypeMapper mapper;

    public AllowanceTypeDto save(AllowanceType entity) {
        return mapper.toDto(repository.save(entity));
    }

    public Optional<AllowanceTypeDto> findById(Integer id) {
        return repository.findById(id).map(mapper::toDto);
    }

    public Optional<AllowanceTypeDto> findByCode(AllowanceCode code) {
        return repository.findByAllowanceCode(code).map(mapper::toDto);
    }

    public List<AllowanceTypeDto> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }
}
