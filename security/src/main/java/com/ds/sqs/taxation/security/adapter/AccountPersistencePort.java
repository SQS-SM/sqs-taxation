package com.ds.sqs.taxation.security.adapter;

import com.ds.sqs.taxation.security.dto.AccountDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountPersistencePort {

    AccountDto save(AccountDto account);

    Optional<AccountDto> findById(Long id);

    Optional<AccountDto> findByGuid(UUID guid);

    List<AccountDto> findAll();

    void deleteById(Long id);
}
