package com.ds.sqs.taxation.employee.repository;
import com.ds.sqs.taxation.employee.entity.PayrollRun;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PayrollRunRepository extends JpaRepository<PayrollRun, Long> {
    List<PayrollRun> findByCompanyId(Long companyId);
}