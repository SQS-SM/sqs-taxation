package com.ds.sqs.taxation.employee.adapter.impl;

import com.ds.sqs.taxation.employee.adapter.EmployeeMedicalAidPersistencePort;
import com.ds.sqs.taxation.employee.dto.EmployeeMedicalAidDto;
import com.ds.sqs.taxation.employee.entity.EmployeeMedicalAid;
import com.ds.sqs.taxation.employee.mapper.EmployeeMedicalAidMapper;
import com.ds.sqs.taxation.employee.repository.EmployeeMedicalAidRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EmployeeMedicalAidPersistenceAdapter implements EmployeeMedicalAidPersistencePort {

    private final EmployeeMedicalAidRepository repository;
    private final EmployeeMedicalAidMapper mapper;

    @Override
    public EmployeeMedicalAidDto save(EmployeeMedicalAidDto employeeMedicalAidDto) {
        var entity = mapper.toEntity(employeeMedicalAidDto);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public Optional<EmployeeMedicalAidDto> findById(Long id) {
        return repository.findById(id).map(mapper::toDto);
    }

    @Override
    public Optional<EmployeeMedicalAidDto> findByCompanyIdAndEmployeeNumber(Long companyId, String employeeNumber) {
        return repository.findByCompanyIdAndEmployeeNumber(companyId, employeeNumber).map(mapper::toDto);
    }

    @Override
    public List<EmployeeMedicalAidDto> findAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }
}
