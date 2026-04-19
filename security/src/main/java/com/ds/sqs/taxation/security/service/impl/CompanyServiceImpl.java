package com.ds.sqs.taxation.security.service.impl;


import com.ds.sqs.taxation.common.entity.Address;
import com.ds.sqs.taxation.common.entity.BankDetails;
import com.ds.sqs.taxation.security.dto.CompanyDto;
import com.ds.sqs.taxation.security.entity.Company;
import com.ds.sqs.taxation.security.entity.Organisation;
import com.ds.sqs.taxation.security.mapper.CompanyMapper;
import com.ds.sqs.taxation.security.adapter.CompanyPersistencePort;
import com.ds.sqs.taxation.security.repository.OrganisationRepository;
import com.ds.sqs.taxation.common.repository.AddressRepository;
import com.ds.sqs.taxation.common.repository.BankDetailsRepository;
import com.ds.sqs.taxation.security.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyPersistencePort persistencePort;
    private final OrganisationRepository organisationRepository;
    private final AddressRepository addressRepository;
    private final BankDetailsRepository bankDetailsRepository;
    private final CompanyMapper mapper;

    @Override
    public CompanyDto create(CompanyDto dto) {

        Company company = mapper.toEntity(dto);

        Organisation organisation =
                organisationRepository.findById(dto.getOrganisationId())
                        .orElseThrow(() -> new RuntimeException("Organisation not found"));

        company.setOrganisation(organisation);

        if (dto.getPhysicalAddressId() != null) {
            Address address =
                    addressRepository.findById(dto.getPhysicalAddressId())
                            .orElseThrow();
            company.setPhysicalAddress(address);
        }

        if (dto.getPostalAddressId() != null) {
            Address address =
                    addressRepository.findById(dto.getPostalAddressId())
                            .orElseThrow();
            company.setPostalAddress(address);
        }

        if (dto.getBankDetailsId() != null) {
            BankDetails bankDetails =
                    bankDetailsRepository.findById(dto.getBankDetailsId())
                            .orElseThrow();
            company.setBankDetails(bankDetails);
        }

        return persistencePort.save(company);
    }

    @Override
    public CompanyDto getById(Long id) {

        return persistencePort.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));
    }

    @Override
    public List<CompanyDto> findAll() {

        return persistencePort.findAll()
                .stream()
                .toList();
    }
}
