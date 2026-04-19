package com.ds.sqs.taxation.employee.mapper;

import com.ds.sqs.taxation.employee.dto.EmployeeBenefitDto;
import com.ds.sqs.taxation.employee.entity.EmployeeBenefit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeBenefitMapper {

    @Mapping(source = "company.id", target = "companyId")
    @Mapping(source = "bankDetails.id", target = "bankDetailsId")
    @Mapping(source = "physicalAddress.id", target = "physicalAddressId")
    @Mapping(source = "postalAddress.id", target = "postalAddressId")
    EmployeeBenefitDto toDto(EmployeeBenefit entity);

    @Mapping(target = "company", ignore = true)
    @Mapping(target = "bankDetails", ignore = true)
    @Mapping(target = "physicalAddress", ignore = true)
    @Mapping(target = "postalAddress", ignore = true)
    EmployeeBenefit toEntity(EmployeeBenefitDto dto);
}
