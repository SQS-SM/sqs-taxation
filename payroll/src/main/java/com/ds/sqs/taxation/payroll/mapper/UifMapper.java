package com.ds.sqs.taxation.payroll.mapper;

import com.ds.sqs.taxation.payroll.dto.UifDto;
import com.ds.sqs.taxation.payroll.entity.Uif;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UifMapper {

    UifDto toDto(Uif entity);

    Uif toEntity(UifDto dto);
}
