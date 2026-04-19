package com.ds.sqs.taxation.payroll.mapper;

import com.ds.sqs.taxation.payroll.dto.TaxThresholdDto;
import com.ds.sqs.taxation.payroll.entity.TaxThreshold;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaxThresholdMapper {

    TaxThresholdDto toDto(TaxThreshold entity);

    TaxThreshold toEntity(TaxThresholdDto dto);
}
