package com.ds.sqs.taxation.employee.mapper;

import com.ds.sqs.taxation.employee.dto.EmployeeAllowanceDto;
import com.ds.sqs.taxation.employee.entity.EmployeeAllowance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeAllowanceMapper {

    @Mapping(source = "company.id", target = "companyId")
    @Mapping(source = "bankDetails.id", target = "bankDetailsId")
    @Mapping(source = "physicalAddress.id", target = "physicalAddressId")
    @Mapping(source = "postalAddress.id", target = "postalAddressId")
    EmployeeAllowanceDto toDto(EmployeeAllowance entity);

    @Mapping(target = "company", ignore = true)
    @Mapping(target = "bankDetails", ignore = true)
    @Mapping(target = "physicalAddress", ignore = true)
    @Mapping(target = "postalAddress", ignore = true)
    EmployeeAllowance toEntity(EmployeeAllowanceDto dto);
}
