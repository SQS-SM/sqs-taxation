package com.ds.sqs.taxation.payroll.mapper;

import com.ds.sqs.taxation.payroll.dto.PensionFundDto;
import com.ds.sqs.taxation.payroll.entity.PensionFund;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PensionFundMapper {

    PensionFundDto toDto(PensionFund entity);

    PensionFund toEntity(PensionFundDto dto);
}
