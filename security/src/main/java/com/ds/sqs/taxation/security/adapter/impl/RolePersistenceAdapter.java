package com.ds.sqs.taxation.security.adapter.impl;
import com.ds.sqs.taxation.security.dto.RoleDto;
import com.ds.sqs.taxation.security.adapter.RolePersistencePort;
import com.ds.sqs.taxation.security.mapper.RoleMapper;
import com.ds.sqs.taxation.security.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RolePersistenceAdapter implements RolePersistencePort {

    private final RoleRepository repository;
    private final RoleMapper mapper;

    @Override
    @Caching(evict = {
            @CacheEvict(cacheNames = "roleById", allEntries = true),
            @CacheEvict(cacheNames = "roleByCode", allEntries = true),
            @CacheEvict(cacheNames = "roleAll", allEntries = true)
    })
    public RoleDto save(RoleDto role) {
        var entity = mapper.toEntity(role);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    @Cacheable(cacheNames = "roleById", key = "#id")
    public Optional<RoleDto> findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    @Cacheable(cacheNames = "roleByCode", key = "#code")
    public Optional<RoleDto> findByCode(String code) {
        return repository.findByCode(code)
                .map(mapper::toDto);
    }

    @Override
    @Cacheable(cacheNames = "roleAll", key = "'all'")
    public List<RoleDto> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDto).toList();
    }
}
