package com.ds.sqs.taxation.employee.service.impl;

import com.ds.sqs.taxation.common.entity.Address;
import com.ds.sqs.taxation.common.entity.BankDetails;
import com.ds.sqs.taxation.common.repository.AddressRepository;
import com.ds.sqs.taxation.common.repository.BankDetailsRepository;
import com.ds.sqs.taxation.employee.dto.EmployeeDto;
import com.ds.sqs.taxation.employee.entity.Employee;
import com.ds.sqs.taxation.employee.mapper.EmployeeMapper;
import com.ds.sqs.taxation.employee.repository.EmployeeRepository;
import com.ds.sqs.taxation.employee.service.EmployeeCrudService;
import com.ds.sqs.taxation.security.entity.Company;
import com.ds.sqs.taxation.security.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeCrudServiceImpl implements EmployeeCrudService {

    private final EmployeeRepository employeeRepository;
    private final CompanyRepository companyRepository;
    private final AddressRepository addressRepository;
    private final BankDetailsRepository bankDetailsRepository;
    private final EmployeeMapper mapper;

    @Override
    public EmployeeDto create(EmployeeDto dto) {
        Employee employee = mapper.toEntity(dto);
        hydrateRelations(employee, dto);
        return mapper.toDto(employeeRepository.save(employee));
    }

    @Override
    public EmployeeDto getById(Long id) {
        return employeeRepository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    @Override
    public List<EmployeeDto> findByFilters(Long companyId, Long organisationId) {
        if (companyId != null && organisationId != null) {
            return employeeRepository.findByCompanyIdAndCompanyOrganisationId(companyId, organisationId).stream()
                    .map(mapper::toDto)
                    .toList();
        }
        if (companyId != null) {
            return employeeRepository.findByCompanyId(companyId).stream()
                    .map(mapper::toDto)
                    .toList();
        }
        if (organisationId != null) {
            return employeeRepository.findByCompanyOrganisationId(organisationId).stream()
                    .map(mapper::toDto)
                    .toList();
        }
        return employeeRepository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public EmployeeDto update(Long id, EmployeeDto dto) {
        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Employee employee = mapper.toEntity(dto);
        employee.setId(id);
        employee.setGuid(existing.getGuid());
        hydrateRelations(employee, dto);
        return mapper.toDto(employeeRepository.save(employee));
    }

    @Override
    public void delete(Long id) {
        getById(id);
        employeeRepository.deleteById(id);
    }

    private void hydrateRelations(Employee employee, EmployeeDto dto) {
        Company company = companyRepository.findById(dto.getCompanyId())
                .orElseThrow(() -> new RuntimeException("Company not found"));
        employee.setCompany(company);

        if (dto.getBankDetailsId() != null) {
            BankDetails bankDetails = bankDetailsRepository.findById(dto.getBankDetailsId())
                    .orElseThrow(() -> new RuntimeException("Bank details not found"));
            employee.setBankDetails(bankDetails);
        } else {
            employee.setBankDetails(null);
        }

        if (dto.getPhysicalAddressId() != null) {
            Address address = addressRepository.findById(dto.getPhysicalAddressId())
                    .orElseThrow(() -> new RuntimeException("Physical address not found"));
            employee.setPhysicalAddress(address);
        } else {
            employee.setPhysicalAddress(null);
        }

        if (dto.getPostalAddressId() != null) {
            Address address = addressRepository.findById(dto.getPostalAddressId())
                    .orElseThrow(() -> new RuntimeException("Postal address not found"));
            employee.setPostalAddress(address);
        } else {
            employee.setPostalAddress(null);
        }
    }
}
