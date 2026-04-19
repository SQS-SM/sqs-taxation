package com.ds.sqs.taxation.payroll.mapper;

import com.ds.sqs.taxation.payroll.dto.AllowanceTypeDto;
import com.ds.sqs.taxation.payroll.entity.AllowanceType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AllowanceTypeMapper {

    AllowanceTypeDto toDto(AllowanceType entity);

    AllowanceType toEntity(AllowanceTypeDto dto);
}
