package com.ds.sqs.taxation.payroll.repository;

import com.ds.sqs.taxation.payroll.entity.AllowanceType;
import com.ds.sqs.taxation.payroll.enums.AllowanceCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AllowanceTypeRepository
        extends JpaRepository<AllowanceType, Integer> {

    Optional<AllowanceType> findByAllowanceCode(AllowanceCode code);
}
