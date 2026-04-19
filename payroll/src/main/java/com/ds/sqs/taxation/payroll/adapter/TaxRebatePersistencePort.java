package com.ds.sqs.taxation.payroll.adapter;

import com.ds.sqs.taxation.payroll.dto.TaxRebateDto;
import com.ds.sqs.taxation.payroll.entity.TaxRebate;

import java.util.List;
import java.util.Optional;

public interface TaxRebatePersistencePort {

    TaxRebateDto save(TaxRebate entity);

    Optional<TaxRebateDto> findById(Integer id);

    List<TaxRebateDto> findByYear(String year);

    Optional<TaxRebateDto> findByRebateDescriptionAndYear(String rebateDescription, String year);

    List<TaxRebateDto> findAll();
}
