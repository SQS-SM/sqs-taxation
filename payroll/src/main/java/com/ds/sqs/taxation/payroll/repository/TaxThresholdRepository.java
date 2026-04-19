package com.ds.sqs.taxation.payroll.repository;

import com.ds.sqs.taxation.payroll.entity.TaxThreshold;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaxThresholdRepository extends JpaRepository<TaxThreshold, Integer> {

    List<TaxThreshold> findByYear(String year);
}
