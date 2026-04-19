package com.ds.sqs.taxation.security.adapter.impl;
import com.ds.sqs.taxation.security.dto.RoleDto;
import com.ds.sqs.taxation.security.adapter.RolePersistencePort;
import com.ds.sqs.taxation.security.mapper.RoleMapper;
import com.ds.sqs.taxation.security.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RolePersistenceAdapter implements RolePersistencePort {

    private final RoleRepository repository;
    private final RoleMapper mapper;

    @Override
    public RoleDto save(RoleDto role) {
        var entity = mapper.toEntity(role);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public Optional<RoleDto> findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    public Optional<RoleDto> findByCode(String code) {
        return repository.findByCode(code)
                .map(mapper::toDto);
    }

    @Override
    public List<RoleDto> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDto).toList();
    }
}