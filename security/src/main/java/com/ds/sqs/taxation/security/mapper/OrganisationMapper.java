package com.ds.sqs.taxation.security.mapper;

import com.ds.sqs.taxation.security.dto.OrganisationDto;
import com.ds.sqs.taxation.security.entity.Organisation;
import org.springframework.stereotype.Component;

@Component
public class OrganisationMapper {

    public OrganisationDto toDto(Organisation entity) {
        if ( entity == null ) {
            return null;
        }

        OrganisationDto organisationDto = new OrganisationDto();

        organisationDto.setId( entity.getId() );
        organisationDto.setGuid( entity.getGuid() );
        organisationDto.setName( entity.getName() );

        return organisationDto;
    }

    public Organisation toEntity(OrganisationDto dto) {
        if ( dto == null ) {
            return null;
        }

        Organisation organisation = new Organisation();

        organisation.setId( dto.getId() );
        organisation.setGuid( dto.getGuid() );
        organisation.setName( dto.getName() );

        return organisation;
    }
}
