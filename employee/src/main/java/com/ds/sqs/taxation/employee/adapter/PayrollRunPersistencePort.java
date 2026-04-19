package com.ds.sqs.taxation.employee.adapter;

import com.ds.sqs.taxation.employee.dto.PayrollRunDto;
import com.ds.sqs.taxation.employee.entity.PayrollRun;

import java.util.List;
import java.util.Optional;

public interface PayrollRunPersistencePort {

    PayrollRunDto save(PayrollRunDto entity);

    Optional<PayrollRunDto> findById(Long id);

    List<PayrollRunDto> findByCompanyId(Long companyId);

    List<PayrollRunDto> findAll();
}
