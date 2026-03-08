package com.ds.sqs.taxation.security.repository;

import com.ds.sqs.taxation.security.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    Optional<Company> findByGuid(UUID guid);

    List<Company> findByOrganisationId(Long organisationId);

    boolean existsByName(String name);
}
