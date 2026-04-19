package com.ds.sqs.taxation.employee.adapter;

import com.ds.sqs.taxation.employee.dto.EmployeePayrollResultDto;
import com.ds.sqs.taxation.employee.entity.EmployeePayrollResult;

import java.util.List;
import java.util.Optional;

public interface EmployeePayrollResultPersistencePort {

    EmployeePayrollResultDto save(EmployeePayrollResultDto entity);

    Optional<EmployeePayrollResultDto> findById(Long id);

    List<EmployeePayrollResultDto> findByPayrollRunId(Long payrollRunId);

    List<EmployeePayrollResultDto> findAll();
}
