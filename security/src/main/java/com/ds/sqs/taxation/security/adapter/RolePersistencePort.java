package com.ds.sqs.taxation.security.adapter;

import com.ds.sqs.taxation.security.dto.RoleDto;

import java.util.List;
import java.util.Optional;

public interface RolePersistencePort {

    RoleDto save(RoleDto role);

    Optional<RoleDto> findById(Long id);

    Optional<RoleDto> findByCode(String code);

    List<RoleDto> findAll();
}