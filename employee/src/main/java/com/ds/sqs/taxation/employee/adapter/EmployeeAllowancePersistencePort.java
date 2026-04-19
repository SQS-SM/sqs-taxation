package com.ds.sqs.taxation.employee.adapter;

import com.ds.sqs.taxation.employee.dto.EmployeeAllowanceDto;
import com.ds.sqs.taxation.employee.entity.EmployeeAllowance;

import java.util.List;
import java.util.Optional;

public interface EmployeeAllowancePersistencePort {

    EmployeeAllowanceDto save(EmployeeAllowanceDto entity);

    Optional<EmployeeAllowanceDto> findById(Long id);

    Optional<EmployeeAllowanceDto> findByCompanyIdAndEmployeeNumber(Long companyId, String employeeNumber);

    List<EmployeeAllowanceDto> findAll();
}
