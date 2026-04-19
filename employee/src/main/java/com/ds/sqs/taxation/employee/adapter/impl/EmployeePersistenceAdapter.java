package com.ds.sqs.taxation.employee.adapter.impl;

import com.ds.sqs.taxation.employee.adapter.EmployeePersistencePort;
import com.ds.sqs.taxation.employee.dto.EmployeeDto;
import com.ds.sqs.taxation.employee.entity.Employee;
import com.ds.sqs.taxation.employee.mapper.EmployeeMapper;
import com.ds.sqs.taxation.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EmployeePersistenceAdapter implements EmployeePersistencePort {

    private final EmployeeRepository repository;
    private final EmployeeMapper mapper;

    @Override
    public EmployeeDto save(EmployeeDto employeeDto) {
        var entity = mapper.toEntity(employeeDto);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public Optional<EmployeeDto> findById(Long id) {
        return repository.findById(id).map(mapper::toDto);
    }

    @Override
    public Optional<EmployeeDto> findByCompanyIdAndEmployeeNumber(Long companyId, String employeeNumber) {
        return repository.findByCompanyIdAndEmployeeNumber(companyId, employeeNumber).map(mapper::toDto);
    }

    @Override
    public List<EmployeeDto> findAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }
}
