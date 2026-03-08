package com.ds.sqs.taxation.employee.service;

import com.ds.sqs.taxation.employee.dto.EmployeeDto;

import java.util.List;

public interface EmployeeCrudService {

    EmployeeDto create(EmployeeDto dto);

    EmployeeDto getById(Long id);

    List<EmployeeDto> findByFilters(Long companyId, Long organisationId);

    EmployeeDto update(Long id, EmployeeDto dto);

    void delete(Long id);
}
