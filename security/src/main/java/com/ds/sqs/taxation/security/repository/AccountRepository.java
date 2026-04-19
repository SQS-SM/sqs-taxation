package com.ds.sqs.taxation.security.repository;

import com.ds.sqs.taxation.security.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByUsername(String username);

    Optional<Account> findByGuid(UUID guid);

    boolean existsByUsername(String username);
}