package com.ds.sqs.taxation.payroll.mapper;

import com.ds.sqs.taxation.payroll.dto.BenefitDto;
import com.ds.sqs.taxation.payroll.entity.Benefit;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BenefitMapper {

    BenefitDto toDto(Benefit entity);

    Benefit toEntity(BenefitDto dto);
}
