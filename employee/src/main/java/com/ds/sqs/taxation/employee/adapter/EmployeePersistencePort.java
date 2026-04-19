package com.ds.sqs.taxation.employee.adapter;

import com.ds.sqs.taxation.employee.dto.EmployeeDto;
import com.ds.sqs.taxation.employee.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeePersistencePort {

    EmployeeDto save(EmployeeDto entity);

    Optional<EmployeeDto> findById(Long id);

    Optional<EmployeeDto> findByCompanyIdAndEmployeeNumber(Long companyId, String employeeNumber);

    List<EmployeeDto> findAll();
}
