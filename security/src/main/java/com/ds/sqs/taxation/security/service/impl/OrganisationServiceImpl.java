package com.ds.sqs.taxation.security.service.impl;

import com.ds.sqs.taxation.security.adapter.OrganisationPersistencePort;
import com.ds.sqs.taxation.security.dto.OrganisationDto;
import com.ds.sqs.taxation.security.service.OrganisationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganisationServiceImpl implements OrganisationService {

    private final OrganisationPersistencePort persistencePort;

    @Override
    public OrganisationDto create(OrganisationDto dto) {
        return persistencePort.save(dto);
    }

    @Override
    public OrganisationDto getById(Long id) {
        return persistencePort.findById(id)
                .orElseThrow(() -> new RuntimeException("Organisation not found"));
    }

    @Override
    public List<OrganisationDto> findAll() {
        return persistencePort.findAll();
    }

    @Override
    public OrganisationDto update(Long id, OrganisationDto dto) {
        getById(id);
        dto.setId(id);
        return persistencePort.save(dto);
    }

    @Override
    public void delete(Long id) {
        getById(id);
        persistencePort.deleteById(id);
    }
}
