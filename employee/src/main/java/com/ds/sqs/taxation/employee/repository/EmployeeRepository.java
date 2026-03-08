package com.ds.sqs.taxation.employee.repository;
import com.ds.sqs.taxation.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByCompanyIdAndEmployeeNumber(Long companyId, String employeeNumber);

    List<Employee> findByCompanyId(Long companyId);

    List<Employee> findByCompanyOrganisationId(Long organisationId);

    List<Employee> findByCompanyIdAndCompanyOrganisationId(Long companyId, Long organisationId);
}
