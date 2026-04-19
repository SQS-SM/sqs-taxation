package com.ds.sqs.taxation.security.mapper;

import com.ds.sqs.taxation.security.dto.CompanyDto;
import com.ds.sqs.taxation.security.entity.Company;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    @Mapping(source = "organisation.id", target = "organisationId")
    @Mapping(source = "physicalAddress.id", target = "physicalAddressId")
    @Mapping(source = "postalAddress.id", target = "postalAddressId")
    @Mapping(source = "bankDetails.id", target = "bankDetailsId")
    CompanyDto toDto(Company entity);

    @Mapping(target = "organisation", ignore = true)
    @Mapping(target = "physicalAddress", ignore = true)
    @Mapping(target = "postalAddress", ignore = true)
    @Mapping(target = "bankDetails", ignore = true)
    Company toEntity(CompanyDto dto);
}
