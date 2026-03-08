package com.ds.sqs.taxation.security.repository;

import com.ds.sqs.taxation.security.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByUsername(String username);

    Optional<Account> findByGuid(UUID guid);

    Optional<Account> findByPasswordResetKey(UUID passwordResetKey);

    List<Account> findByCompanyId(Long companyId);

    List<Account> findByOrganisationId(Long organisationId);

    List<Account> findByCompanyIdAndOrganisationId(Long companyId, Long organisationId);

    boolean existsByUsername(String username);
}
