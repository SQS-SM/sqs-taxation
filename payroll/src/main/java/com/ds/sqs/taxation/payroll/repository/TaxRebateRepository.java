package com.ds.sqs.taxation.payroll.repository;


import com.ds.sqs.taxation.payroll.entity.TaxRebate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaxRebateRepository extends JpaRepository<TaxRebate, Integer> {
    List<TaxRebate> findByYear(String year);

    Optional<TaxRebate> findByRebateDescriptionAndYear(String rebateDescription, String year);

}