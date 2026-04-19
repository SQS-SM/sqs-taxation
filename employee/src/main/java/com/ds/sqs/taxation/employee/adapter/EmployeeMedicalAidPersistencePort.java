package com.ds.sqs.taxation.employee.adapter;

import com.ds.sqs.taxation.employee.dto.EmployeeMedicalAidDto;
import com.ds.sqs.taxation.employee.entity.EmployeeMedicalAid;

import java.util.List;
import java.util.Optional;

public interface EmployeeMedicalAidPersistencePort {

    EmployeeMedicalAidDto save(EmployeeMedicalAidDto entity);

    Optional<EmployeeMedicalAidDto> findById(Long id);

    Optional<EmployeeMedicalAidDto> findByCompanyIdAndEmployeeNumber(Long companyId, String employeeNumber);

    List<EmployeeMedicalAidDto> findAll();
}
