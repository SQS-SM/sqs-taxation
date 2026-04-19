package com.ds.sqs.taxation.payroll.mapper;

import com.ds.sqs.taxation.payroll.dto.TaxRebateDto;
import com.ds.sqs.taxation.payroll.entity.TaxRebate;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaxRebateMapper {

    TaxRebateDto toDto(TaxRebate entity);

    TaxRebate toEntity(TaxRebateDto dto);
}
