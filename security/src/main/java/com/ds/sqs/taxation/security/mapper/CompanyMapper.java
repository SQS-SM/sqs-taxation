package com.ds.sqs.taxation.security.mapper;

import com.ds.sqs.taxation.common.entity.Address;
import com.ds.sqs.taxation.common.entity.BankDetails;
import com.ds.sqs.taxation.security.dto.CompanyDto;
import com.ds.sqs.taxation.security.entity.Company;
import com.ds.sqs.taxation.security.entity.Organisation;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {

    public CompanyDto toDto(Company entity) {
        if ( entity == null ) {
            return null;
        }

        CompanyDto companyDto = new CompanyDto();

        companyDto.setOrganisationId( entityOrganisationId( entity ) );
        companyDto.setPhysicalAddressId( entityPhysicalAddressId( entity ) );
        companyDto.setPostalAddressId( entityPostalAddressId( entity ) );
        companyDto.setBankDetailsId( entityBankDetailsId( entity ) );
        companyDto.setId( entity.getId() );
        companyDto.setGuid( entity.getGuid() );
        companyDto.setName( entity.getName() );
        companyDto.setRegisteredName( entity.getRegisteredName() );
        companyDto.setTradingName( entity.getTradingName() );
        companyDto.setNatureOfBusiness( entity.getNatureOfBusiness() );
        companyDto.setCompanyRegistrationNumber( entity.getCompanyRegistrationNumber() );
        companyDto.setTaxNumber( entity.getTaxNumber() );

        return companyDto;
    }

    public Company toEntity(CompanyDto dto) {
        if ( dto == null ) {
            return null;
        }

        Company company = new Company();

        company.setId( dto.getId() );
        company.setGuid( dto.getGuid() );
        company.setName( dto.getName() );
        company.setRegisteredName( dto.getRegisteredName() );
        company.setTradingName( dto.getTradingName() );
        company.setNatureOfBusiness( dto.getNatureOfBusiness() );
        company.setCompanyRegistrationNumber( dto.getCompanyRegistrationNumber() );
        company.setTaxNumber( dto.getTaxNumber() );

        return company;
    }

    private Long entityOrganisationId(Company company) {
        if ( company == null ) {
            return null;
        }
        Organisation organisation = company.getOrganisation();
        if ( organisation == null ) {
            return null;
        }
        Long id = organisation.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long entityPhysicalAddressId(Company company) {
        if ( company == null ) {
            return null;
        }
        Address physicalAddress = company.getPhysicalAddress();
        if ( physicalAddress == null ) {
            return null;
        }
        Long id = physicalAddress.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long entityPostalAddressId(Company company) {
        if ( company == null ) {
            return null;
        }
        Address postalAddress = company.getPostalAddress();
        if ( postalAddress == null ) {
            return null;
        }
        Long id = postalAddress.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long entityBankDetailsId(Company company) {
        if ( company == null ) {
            return null;
        }
        BankDetails bankDetails = company.getBankDetails();
        if ( bankDetails == null ) {
            return null;
        }
        Long id = bankDetails.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
