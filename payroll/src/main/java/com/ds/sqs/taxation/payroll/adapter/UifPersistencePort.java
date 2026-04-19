package com.ds.sqs.taxation.payroll.adapter;

import com.ds.sqs.taxation.payroll.dto.UifDto;
import com.ds.sqs.taxation.payroll.entity.Uif;

import java.util.List;
import java.util.Optional;

public interface UifPersistencePort extends EffectiveDateReferencePort<UifDto> {

    UifDto save(Uif entity);

    Optional<UifDto> findById(Integer id);

    Optional<UifDto> findCurrent();

    List<UifDto> findAll();
}
