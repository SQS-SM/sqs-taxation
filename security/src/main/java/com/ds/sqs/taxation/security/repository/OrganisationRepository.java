package com.ds.sqs.taxation.security.repository;

import com.ds.sqs.taxation.security.entity.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OrganisationRepository
        extends JpaRepository<Organisation, Long> {

    Optional<Organisation> findByGuid(UUID guid);

    boolean existsByName(String name);
}