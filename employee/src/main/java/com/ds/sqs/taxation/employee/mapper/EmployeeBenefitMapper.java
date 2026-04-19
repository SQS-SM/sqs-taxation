package com.ds.sqs.taxation.employee.mapper;

import com.ds.sqs.taxation.common.entity.Address;
import com.ds.sqs.taxation.common.entity.BankDetails;
import com.ds.sqs.taxation.employee.dto.EmployeeBenefitDto;
import com.ds.sqs.taxation.employee.entity.EmployeeBenefit;
import com.ds.sqs.taxation.security.entity.Company;
import org.springframework.stereotype.Component;

@Component
public class EmployeeBenefitMapper {

    public EmployeeBenefitDto toDto(EmployeeBenefit entity) {
        if ( entity == null ) {
            return null;
        }

        EmployeeBenefitDto employeeBenefitDto = new EmployeeBenefitDto();

        employeeBenefitDto.setCompanyId( entityCompanyId( entity ) );
        employeeBenefitDto.setBankDetailsId( entityBankDetailsId( entity ) );
        employeeBenefitDto.setPhysicalAddressId( entityPhysicalAddressId( entity ) );
        employeeBenefitDto.setPostalAddressId( entityPostalAddressId( entity ) );
        employeeBenefitDto.setId( entity.getId() );
        employeeBenefitDto.setGuid( entity.getGuid() );
        employeeBenefitDto.setEmployeeNumber( entity.getEmployeeNumber() );
        employeeBenefitDto.setFirstName( entity.getFirstName() );
        employeeBenefitDto.setLastName( entity.getLastName() );
        employeeBenefitDto.setEmploymentType( entity.getEmploymentType() );
        employeeBenefitDto.setBasicSalary( entity.getBasicSalary() );

        return employeeBenefitDto;
    }


    public EmployeeBenefit toEntity(EmployeeBenefitDto dto) {
        if ( dto == null ) {
            return null;
        }

        EmployeeBenefit employeeBenefit = new EmployeeBenefit();

        employeeBenefit.setId( dto.getId() );
        employeeBenefit.setGuid( dto.getGuid() );
        employeeBenefit.setEmployeeNumber( dto.getEmployeeNumber() );
        employeeBenefit.setFirstName( dto.getFirstName() );
        employeeBenefit.setLastName( dto.getLastName() );
        employeeBenefit.setEmploymentType( dto.getEmploymentType() );
        employeeBenefit.setBasicSalary( dto.getBasicSalary() );

        return employeeBenefit;
    }

    private Long entityCompanyId(EmployeeBenefit employeeBenefit) {
        if ( employeeBenefit == null ) {
            return null;
        }
        Company company = employeeBenefit.getCompany();
        if ( company == null ) {
            return null;
        }
        Long id = company.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long entityBankDetailsId(EmployeeBenefit employeeBenefit) {
        if ( employeeBenefit == null ) {
            return null;
        }
        BankDetails bankDetails = employeeBenefit.getBankDetails();
        if ( bankDetails == null ) {
            return null;
        }
        Long id = bankDetails.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long entityPhysicalAddressId(EmployeeBenefit employeeBenefit) {
        if ( employeeBenefit == null ) {
            return null;
        }
        Address physicalAddress = employeeBenefit.getPhysicalAddress();
        if ( physicalAddress == null ) {
            return null;
        }
        Long id = physicalAddress.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long entityPostalAddressId(EmployeeBenefit employeeBenefit) {
        if ( employeeBenefit == null ) {
            return null;
        }
        Address postalAddress = employeeBenefit.getPostalAddress();
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

