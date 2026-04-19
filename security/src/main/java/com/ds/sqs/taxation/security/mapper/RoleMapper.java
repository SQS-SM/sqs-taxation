package com.ds.sqs.taxation.security.mapper;

import com.ds.sqs.taxation.security.dto.RoleDto;
import com.ds.sqs.taxation.security.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleDto toDto(Role entity);

    Role toEntity(RoleDto dto);
}
