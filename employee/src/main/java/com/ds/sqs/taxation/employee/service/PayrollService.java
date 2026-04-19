package com.ds.sqs.taxation.employee.service;

import com.ds.sqs.taxation.employee.entity.EmployeePayrollResult;
import com.ds.sqs.taxation.employee.entity.PayrollRun;
import com.ds.sqs.taxation.employee.repository.EmployeePayrollResultRepository;
import com.ds.sqs.taxation.employee.repository.PayrollRunRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PayrollService {

    private final PayrollRunRepository payrollRunRepository;
    private final EmployeePayrollResultRepository resultRepository;

    public PayrollService(PayrollRunRepository payrollRunRepository,
                          EmployeePayrollResultRepository resultRepository) {
        this.payrollRunRepository = payrollRunRepository;
        this.resultRepository = resultRepository;
    }

    public PayrollRun createRun(PayrollRun run) {
        return payrollRunRepository.save(run);
    }

    public EmployeePayrollResult saveResult(EmployeePayrollResult result) {
        return resultRepository.save(result);
    }

    public List<EmployeePayrollResult> getResults(Long payrollRunId) {
        return resultRepository.findByPayrollRunId(payrollRunId);
    }
}
