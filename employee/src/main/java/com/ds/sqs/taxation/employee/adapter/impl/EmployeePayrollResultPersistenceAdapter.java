package com.ds.sqs.taxation.employee.adapter.impl;

import com.ds.sqs.taxation.employee.adapter.EmployeePayrollResultPersistencePort;
import com.ds.sqs.taxation.employee.dto.EmployeePayrollResultDto;
import com.ds.sqs.taxation.employee.entity.EmployeePayrollResult;
import com.ds.sqs.taxation.employee.mapper.EmployeePayrollResultMapper;
import com.ds.sqs.taxation.employee.repository.EmployeePayrollResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EmployeePayrollResultPersistenceAdapter implements EmployeePayrollResultPersistencePort {

    private final EmployeePayrollResultRepository repository;
    private final EmployeePayrollResultMapper mapper;

    @Override
    public EmployeePayrollResultDto save(EmployeePayrollResultDto employeePayrollResultDto) {
        var entity = mapper.toEntity(employeePayrollResultDto);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public Optional<EmployeePayrollResultDto> findById(Long id) {
        return repository.findById(id).map(mapper::toDto);
    }

    @Override
    public List<EmployeePayrollResultDto> findByPayrollRunId(Long payrollRunId) {
        return repository.findByPayrollRunId(payrollRunId).stream().map(mapper::toDto).toList();
    }

    @Override
    public List<EmployeePayrollResultDto> findAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }
}
