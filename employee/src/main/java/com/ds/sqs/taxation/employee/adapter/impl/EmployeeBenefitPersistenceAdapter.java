package com.ds.sqs.taxation.employee.adapter.impl;

import com.ds.sqs.taxation.employee.adapter.EmployeeBenefitPersistencePort;
import com.ds.sqs.taxation.employee.dto.EmployeeBenefitDto;
import com.ds.sqs.taxation.employee.entity.EmployeeBenefit;
import com.ds.sqs.taxation.employee.mapper.EmployeeBenefitMapper;
import com.ds.sqs.taxation.employee.repository.EmployeeBenefitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EmployeeBenefitPersistenceAdapter implements EmployeeBenefitPersistencePort {

    private final EmployeeBenefitRepository repository;
    private final EmployeeBenefitMapper mapper;

    @Override
    public EmployeeBenefitDto save(EmployeeBenefitDto employeeBenefitDto) {
        var entity = mapper.toEntity(employeeBenefitDto);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public Optional<EmployeeBenefitDto> findById(Long id) {
        return repository.findById(id).map(mapper::toDto);
    }

    @Override
    public Optional<EmployeeBenefitDto> findByCompanyIdAndEmployeeNumber(Long companyId, String employeeNumber) {
        return repository.findByCompanyIdAndEmployeeNumber(companyId, employeeNumber).map(mapper::toDto);
    }

    @Override
    public List<EmployeeBenefitDto> findAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }
}
