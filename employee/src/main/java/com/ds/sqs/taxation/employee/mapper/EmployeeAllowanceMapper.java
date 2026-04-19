package com.ds.sqs.taxation.employee.mapper;

import com.ds.sqs.taxation.common.entity.Address;
import com.ds.sqs.taxation.common.entity.BankDetails;
import com.ds.sqs.taxation.employee.dto.EmployeeAllowanceDto;
import com.ds.sqs.taxation.employee.entity.EmployeeAllowance;
import com.ds.sqs.taxation.security.entity.Company;
import org.springframework.stereotype.Component;

@Component
public class EmployeeAllowanceMapper {

    public EmployeeAllowanceDto toDto(EmployeeAllowance entity) {
        if ( entity == null ) {
            return null;
        }

        EmployeeAllowanceDto employeeAllowanceDto = new EmployeeAllowanceDto();

        employeeAllowanceDto.setCompanyId( entityCompanyId( entity ) );
        employeeAllowanceDto.setBankDetailsId( entityBankDetailsId( entity ) );
        employeeAllowanceDto.setPhysicalAddressId( entityPhysicalAddressId( entity ) );
        employeeAllowanceDto.setPostalAddressId( entityPostalAddressId( entity ) );
        employeeAllowanceDto.setId( entity.getId() );
        employeeAllowanceDto.setGuid( entity.getGuid() );
        employeeAllowanceDto.setEmployeeNumber( entity.getEmployeeNumber() );
        employeeAllowanceDto.setFirstName( entity.getFirstName() );
        employeeAllowanceDto.setLastName( entity.getLastName() );
        employeeAllowanceDto.setEmploymentType( entity.getEmploymentType() );
        employeeAllowanceDto.setBasicSalary( entity.getBasicSalary() );

        return employeeAllowanceDto;
    }


    public EmployeeAllowance toEntity(EmployeeAllowanceDto dto) {
        if ( dto == null ) {
            return null;
        }

        EmployeeAllowance employeeAllowance = new EmployeeAllowance();

        employeeAllowance.setId( dto.getId() );
        employeeAllowance.setGuid( dto.getGuid() );
        employeeAllowance.setEmployeeNumber( dto.getEmployeeNumber() );
        employeeAllowance.setFirstName( dto.getFirstName() );
        employeeAllowance.setLastName( dto.getLastName() );
        employeeAllowance.setEmploymentType( dto.getEmploymentType() );
        employeeAllowance.setBasicSalary( dto.getBasicSalary() );

        return employeeAllowance;
    }

    private Long entityCompanyId(EmployeeAllowance employeeAllowance) {
        if ( employeeAllowance == null ) {
            return null;
        }
        Company company = employeeAllowance.getCompany();
        if ( company == null ) {
            return null;
        }
        Long id = company.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long entityBankDetailsId(EmployeeAllowance employeeAllowance) {
        if ( employeeAllowance == null ) {
            return null;
        }
        BankDetails bankDetails = employeeAllowance.getBankDetails();
        if ( bankDetails == null ) {
            return null;
        }
        Long id = bankDetails.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long entityPhysicalAddressId(EmployeeAllowance employeeAllowance) {
        if ( employeeAllowance == null ) {
            return null;
        }
        Address physicalAddress = employeeAllowance.getPhysicalAddress();
        if ( physicalAddress == null ) {
            return null;
        }
        Long id = physicalAddress.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long entityPostalAddressId(EmployeeAllowance employeeAllowance) {
        if ( employeeAllowance == null ) {
            return null;
        }
        Address postalAddress = employeeAllowance.getPostalAddress();
        if ( postalAddress == null ) {
            return null;
        }
        Long id = postalAddress.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}

