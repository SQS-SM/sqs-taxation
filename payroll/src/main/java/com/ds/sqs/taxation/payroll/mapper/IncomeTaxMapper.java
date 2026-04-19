package com.ds.sqs.taxation.payroll.mapper;

import com.ds.sqs.taxation.payroll.dto.IncomeTaxDto;
import com.ds.sqs.taxation.payroll.entity.IncomeTax;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IncomeTaxMapper {

    IncomeTaxDto toDto(IncomeTax entity);

    IncomeTax toEntity(IncomeTaxDto dto);
}