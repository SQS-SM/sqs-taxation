package com.ds.sqs.taxation.employee.adapter;

import com.ds.sqs.taxation.employee.dto.EmployeeBenefitDto;
import com.ds.sqs.taxation.employee.entity.EmployeeBenefit;

import java.util.List;
import java.util.Optional;

public interface EmployeeBenefitPersistencePort {

    EmployeeBenefitDto save(EmployeeBenefitDto entity);

    Optional<EmployeeBenefitDto> findById(Long id);

    Optional<EmployeeBenefitDto> findByCompanyIdAndEmployeeNumber(Long companyId, String employeeNumber);

    List<EmployeeBenefitDto> findAll();
}
