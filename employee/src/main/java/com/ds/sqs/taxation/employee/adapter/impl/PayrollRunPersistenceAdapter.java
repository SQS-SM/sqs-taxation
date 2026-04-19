package com.ds.sqs.taxation.employee.adapter.impl;

import com.ds.sqs.taxation.employee.adapter.PayrollRunPersistencePort;
import com.ds.sqs.taxation.employee.dto.PayrollRunDto;
import com.ds.sqs.taxation.employee.entity.PayrollRun;
import com.ds.sqs.taxation.employee.mapper.PayrollRunMapper;
import com.ds.sqs.taxation.employee.repository.PayrollRunRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PayrollRunPersistenceAdapter implements PayrollRunPersistencePort {

    private final PayrollRunRepository repository;
    private final PayrollRunMapper mapper;

    @Override
    public PayrollRunDto save(PayrollRunDto payrollRunDto) {
        var entity = mapper.toEntity(payrollRunDto);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public Optional<PayrollRunDto> findById(Long id) {
        return repository.findById(id).map(mapper::toDto);
    }

    @Override
    public List<PayrollRunDto> findByCompanyId(Long companyId) {
        return repository.findByCompanyId(companyId).stream().map(mapper::toDto).toList();
    }

    @Override
    public List<PayrollRunDto> findAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }
}
