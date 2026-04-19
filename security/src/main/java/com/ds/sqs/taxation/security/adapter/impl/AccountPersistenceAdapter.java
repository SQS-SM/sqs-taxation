package com.ds.sqs.taxation.security.adapter.impl;

import com.ds.sqs.taxation.security.adapter.AccountPersistencePort;
import com.ds.sqs.taxation.security.dto.AccountDto;
import com.ds.sqs.taxation.security.mapper.AccountMapper;
import com.ds.sqs.taxation.security.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class AccountPersistenceAdapter implements AccountPersistencePort {

    private final AccountRepository repository;
    private final AccountMapper mapper;

    @Override
    public AccountDto save(AccountDto account) {
        var entity = mapper.toEntity(account);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public Optional<AccountDto> findById(Long id) {
        return repository.findById(id).map(mapper::toDto);
    }

    @Override
    public Optional<AccountDto> findByGuid(UUID guid) {
        return repository.findByGuid(guid).map(mapper::toDto);
    }

    @Override
    public List<AccountDto> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDto).toList();
    }
}