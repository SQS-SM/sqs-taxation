package com.ds.sqs.taxation.payroll.adapter;

import com.ds.sqs.taxation.payroll.dto.TaxThresholdDto;
import com.ds.sqs.taxation.payroll.entity.TaxThreshold;

import java.util.List;
import java.util.Optional;

public interface TaxThresholdPersistencePort {

    TaxThresholdDto save(TaxThreshold entity);

    Optional<TaxThresholdDto> findById(Integer id);

    List<TaxThresholdDto> findByYear(String year);

    List<TaxThresholdDto> findAll();
}
