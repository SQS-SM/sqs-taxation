package com.ds.sqs.taxation.employee.adapter.impl;

import com.ds.sqs.taxation.employee.adapter.EmployeeAllowancePersistencePort;
import com.ds.sqs.taxation.employee.dto.EmployeeAllowanceDto;
import com.ds.sqs.taxation.employee.entity.EmployeeAllowance;
import com.ds.sqs.taxation.employee.mapper.EmployeeAllowanceMapper;
import com.ds.sqs.taxation.employee.repository.EmployeeAllowanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EmployeeAllowancePersistenceAdapter implements EmployeeAllowancePersistencePort {

    private final EmployeeAllowanceRepository repository;
    private final EmployeeAllowanceMapper mapper;

    @Override
    public EmployeeAllowanceDto save(EmployeeAllowanceDto employeeAllowanceDto) {
        var entity = mapper.toEntity(employeeAllowanceDto);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public Optional<EmployeeAllowanceDto> findById(Long id) {
        return repository.findById(id).map(mapper::toDto);
    }

    @Override
    public Optional<EmployeeAllowanceDto> findByCompanyIdAndEmployeeNumber(Long companyId, String employeeNumber) {
        return repository.findByCompanyIdAndEmployeeNumber(companyId, employeeNumber).map(mapper::toDto);
    }

    @Override
    public List<EmployeeAllowanceDto> findAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }
}
