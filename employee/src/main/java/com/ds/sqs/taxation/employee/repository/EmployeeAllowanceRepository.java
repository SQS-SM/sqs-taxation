package com.ds.sqs.taxation.employee.repository;

import com.ds.sqs.taxation.employee.entity.EmployeeAllowance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeAllowanceRepository extends JpaRepository<EmployeeAllowance, Long> {

    Optional<EmployeeAllowance> findByCompanyIdAndEmployeeNumber(Long companyId, String employeeNumber);
}
