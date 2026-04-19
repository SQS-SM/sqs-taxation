package com.ds.sqs.taxation.payroll.adapter;

import com.ds.sqs.taxation.payroll.dto.AllowanceTypeDto;
import com.ds.sqs.taxation.payroll.entity.AllowanceType;
import com.ds.sqs.taxation.payroll.enums.AllowanceCode;

import java.util.List;
import java.util.Optional;

public interface AllowanceTypePersistencePort {

    AllowanceTypeDto save(AllowanceType entity);

    Optional<AllowanceTypeDto> findById(Integer id);

    Optional<AllowanceTypeDto> findByCode(AllowanceCode code);

    List<AllowanceTypeDto> findAll();
}
