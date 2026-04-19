package com.ds.sqs.taxation.employee.repository;

import com.ds.sqs.taxation.employee.entity.EmployeeBenefit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeBenefitRepository extends JpaRepository<EmployeeBenefit, Long> {

    Optional<EmployeeBenefit> findByCompanyIdAndEmployeeNumber(Long companyId, String employeeNumber);
}
