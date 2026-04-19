package com.ds.sqs.taxation.security.mapper;

import com.ds.sqs.taxation.security.dto.RoleDto;
import com.ds.sqs.taxation.security.entity.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    public RoleDto toDto(Role entity) {
        if ( entity == null ) {
            return null;
        }

        RoleDto roleDto = new RoleDto();

        roleDto.setId( entity.getId() );
        roleDto.setCode( entity.getCode() );
        roleDto.setName( entity.getName() );

        return roleDto;
    }

    public Role toEntity(RoleDto dto) {
        if ( dto == null ) {
            return null;
        }

        Role role = new Role();

        role.setId( dto.getId() );
        role.setCode( dto.getCode() );
        role.setName( dto.getName() );

        return role;
    }
}
