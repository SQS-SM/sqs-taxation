package com.ds.sqs.taxation.employee.repository;

import com.ds.sqs.taxation.employee.entity.EmployeePayrollResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeePayrollResultRepository extends JpaRepository<EmployeePayrollResult, Long> {
    List<EmployeePayrollResult> findByPayrollRunId(Long payrollRunId);
}
