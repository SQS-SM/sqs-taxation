package com.ds.sqs.taxation.employee.mapper;

import com.ds.sqs.taxation.common.entity.Address;
import com.ds.sqs.taxation.common.entity.BankDetails;
import com.ds.sqs.taxation.employee.dto.EmployeeMedicalAidDto;
import com.ds.sqs.taxation.employee.entity.EmployeeMedicalAid;
import com.ds.sqs.taxation.security.entity.Company;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMedicalAidMapper {

    public EmployeeMedicalAidDto toDto(EmployeeMedicalAid entity) {
        if ( entity == null ) {
            return null;
        }

        EmployeeMedicalAidDto employeeMedicalAidDto = new EmployeeMedicalAidDto();

        employeeMedicalAidDto.setCompanyId( entityCompanyId( entity ) );
        employeeMedicalAidDto.setBankDetailsId( entityBankDetailsId( entity ) );
        employeeMedicalAidDto.setPhysicalAddressId( entityPhysicalAddressId( entity ) );
        employeeMedicalAidDto.setPostalAddressId( entityPostalAddressId( entity ) );
        employeeMedicalAidDto.setId( entity.getId() );
        employeeMedicalAidDto.setGuid( entity.getGuid() );
        employeeMedicalAidDto.setEmployeeNumber( entity.getEmployeeNumber() );
        employeeMedicalAidDto.setFirstName( entity.getFirstName() );
        employeeMedicalAidDto.setLastName( entity.getLastName() );
        employeeMedicalAidDto.setEmploymentType( entity.getEmploymentType() );
        employeeMedicalAidDto.setBasicSalary( entity.getBasicSalary() );

        return employeeMedicalAidDto;
    }


    public EmployeeMedicalAid toEntity(EmployeeMedicalAidDto dto) {
        if ( dto == null ) {
            return null;
        }

        EmployeeMedicalAid employeeMedicalAid = new EmployeeMedicalAid();

        employeeMedicalAid.setId( dto.getId() );
        employeeMedicalAid.setGuid( dto.getGuid() );
        employeeMedicalAid.setEmployeeNumber( dto.getEmployeeNumber() );
        employeeMedicalAid.setFirstName( dto.getFirstName() );
        employeeMedicalAid.setLastName( dto.getLastName() );
        employeeMedicalAid.setEmploymentType( dto.getEmploymentType() );
        employeeMedicalAid.setBasicSalary( dto.getBasicSalary() );

        return employeeMedicalAid;
    }

    private Long entityCompanyId(EmployeeMedicalAid employeeMedicalAid) {
        if ( employeeMedicalAid == null ) {
            return null;
        }
        Company company = employeeMedicalAid.getCompany();
        if ( company == null ) {
            return null;
        }
        Long id = company.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long entityBankDetailsId(EmployeeMedicalAid employeeMedicalAid) {
        if ( employeeMedicalAid == null ) {
            return null;
        }
        BankDetails bankDetails = employeeMedicalAid.getBankDetails();
        if ( bankDetails == null ) {
            return null;
        }
        Long id = bankDetails.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long entityPhysicalAddressId(EmployeeMedicalAid employeeMedicalAid) {
        if ( employeeMedicalAid == null ) {
            return null;
        }
        Address physicalAddress = employeeMedicalAid.getPhysicalAddress();
        if ( physicalAddress == null ) {
            return null;
        }
        Long id = physicalAddress.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long entityPostalAddressId(EmployeeMedicalAid employeeMedicalAid) {
        if ( employeeMedicalAid == null ) {
            return null;
        }
        Address postalAddress = employeeMedicalAid.getPostalAddress();
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
