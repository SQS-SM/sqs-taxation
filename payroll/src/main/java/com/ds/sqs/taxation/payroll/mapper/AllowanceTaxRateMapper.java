package com.ds.sqs.taxation.payroll.mapper;

import com.ds.sqs.taxation.payroll.dto.AllowanceTaxRateDto;
import com.ds.sqs.taxation.payroll.entity.AllowanceTaxRate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AllowanceTaxRateMapper {

    AllowanceTaxRateDto toDto(AllowanceTaxRate entity);

    @Mapping(target="allowanceType", ignore=true)
    AllowanceTaxRate toEntity(AllowanceTaxRateDto dto);
}
