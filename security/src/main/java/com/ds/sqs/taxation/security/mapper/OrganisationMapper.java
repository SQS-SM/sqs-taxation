package com.ds.sqs.taxation.security.mapper;

import com.ds.sqs.taxation.security.dto.OrganisationDto;
import com.ds.sqs.taxation.security.entity.Organisation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrganisationMapper {

    OrganisationDto toDto(Organisation entity);

    Organisation toEntity(OrganisationDto dto);
}