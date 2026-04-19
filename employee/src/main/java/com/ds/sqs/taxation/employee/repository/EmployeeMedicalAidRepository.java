package com.ds.sqs.taxation.employee.repository;

import com.ds.sqs.taxation.employee.entity.EmployeeMedicalAid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeMedicalAidRepository extends JpaRepository<EmployeeMedicalAid, Long> {

    Optional<EmployeeMedicalAid> findByCompanyIdAndEmployeeNumber(Long companyId, String employeeNumber);
}
