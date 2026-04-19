package com.ds.sqs.taxation.common.repository;

import com.ds.sqs.taxation.common.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProvinceRepository extends JpaRepository<Province, Long> {

    List<Province> findByCountry_Id(Long countryId);

    List<Province> findByCode(String code);
}