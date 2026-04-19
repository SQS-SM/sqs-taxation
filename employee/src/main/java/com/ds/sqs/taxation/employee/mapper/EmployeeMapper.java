package com.ds.sqs.taxation.employee.mapper;

import com.ds.sqs.taxation.employee.dto.EmployeeDto;

import com.ds.sqs.taxation.common.entity.Address;
import com.ds.sqs.taxation.common.entity.BankDetails;
import com.ds.sqs.taxation.employee.entity.Employee;
import com.ds.sqs.taxation.security.entity.Company;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public EmployeeDto toDto(Employee entity) {
        if ( entity == null ) {
            return null;
        }

        EmployeeDto employeeDto = new EmployeeDto();

        employeeDto.setCompanyId( entityCompanyId( entity ) );
        employeeDto.setBankDetailsId( entityBankDetailsId( entity ) );
        employeeDto.setPhysicalAddressId( entityPhysicalAddressId( entity ) );
        employeeDto.setPostalAddressId( entityPostalAddressId( entity ) );
        employeeDto.setId( entity.getId() );
        employeeDto.setGuid( entity.getGuid() );
        employeeDto.setEmployeeNumber( entity.getEmployeeNumber() );
        employeeDto.setFirstName( entity.getFirstName() );
        employeeDto.setLastName( entity.getLastName() );
        employeeDto.setEmploymentType( entity.getEmploymentType() );
        employeeDto.setBasicSalary( entity.getBasicSalary() );

        return employeeDto;
    }


    public Employee toEntity(EmployeeDto dto) {
        if ( dto == null ) {
            return null;
        }

        Employee employee = new Employee();

        employee.setId( dto.getId() );
        employee.setGuid( dto.getGuid() );
        employee.setEmployeeNumber( dto.getEmployeeNumber() );
        employee.setFirstName( dto.getFirstName() );
        employee.setLastName( dto.getLastName() );
        employee.setEmploymentType( dto.getEmploymentType() );
        employee.setBasicSalary( dto.getBasicSalary() );

        return employee;
    }

    private Long entityCompanyId(Employee employee) {
        if ( employee == null ) {
            return null;
        }
        Company company = employee.getCompany();
        if ( company == null ) {
            return null;
        }
        Long id = company.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long entityBankDetailsId(Employee employee) {
        if ( employee == null ) {
            return null;
        }
        BankDetails bankDetails = employee.getBankDetails();
        if ( bankDetails == null ) {
            return null;
        }
        Long id = bankDetails.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long entityPhysicalAddressId(Employee employee) {
        if ( employee == null ) {
            return null;
        }
        Address physicalAddress = employee.getPhysicalAddress();
        if ( physicalAddress == null ) {
            return null;
        }
        Long id = physicalAddress.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long entityPostalAddressId(Employee employee) {
        if ( employee == null ) {
            return null;
        }
        Address postalAddress = employee.getPostalAddress();
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

