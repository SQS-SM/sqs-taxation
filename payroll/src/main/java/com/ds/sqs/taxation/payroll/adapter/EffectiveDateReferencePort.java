package com.ds.sqs.taxation.payroll.adapter;

import java.time.LocalDate;
import java.util.Optional;

public interface EffectiveDateReferencePort<T> {

    Optional<T> findEffective(LocalDate date);
}
