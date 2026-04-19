package com.ds.sqs.taxation.employee.mapper;

import com.ds.sqs.taxation.employee.dto.PayrollRunDto;
import com.ds.sqs.taxation.employee.entity.PayrollRun;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PayrollRunMapper {

    @Mapping(source = "company.id", target = "companyId")
    PayrollRunDto toDto(PayrollRun entity);

    @Mapping(target = "company", ignore = true)
    PayrollRun toEntity(PayrollRunDto dto);
}
