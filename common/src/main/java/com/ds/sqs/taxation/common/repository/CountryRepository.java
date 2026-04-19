package com.ds.sqs.taxation.common.repository;

import com.ds.sqs.taxation.common.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository
        extends JpaRepository<Country, Long> {
}