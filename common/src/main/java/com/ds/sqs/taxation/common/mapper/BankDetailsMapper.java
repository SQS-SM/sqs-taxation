package com.ds.sqs.taxation.common.mapper;

import com.ds.sqs.taxation.common.dto.BankDetailsDto;
import com.ds.sqs.taxation.common.entity.BankDetails;
import org.springframework.stereotype.Component;

@Component
public class BankDetailsMapper {


    public BankDetailsDto toDto(BankDetails entity) {
        if ( entity == null ) {
            return null;
        }

        BankDetailsDto bankDetailsDto = new BankDetailsDto();

        bankDetailsDto.setId( entity.getId() );
        bankDetailsDto.setBankName( entity.getBankName() );
        bankDetailsDto.setAccountHolderName( entity.getAccountHolderName() );
        bankDetailsDto.setAccountType( entity.getAccountType() );
        bankDetailsDto.setBranchCode( entity.getBranchCode() );
        bankDetailsDto.setBranchName( entity.getBranchName() );
        bankDetailsDto.setAccountNumber( entity.getAccountNumber() );

        return bankDetailsDto;
    }


    public BankDetails toEntity(BankDetailsDto dto) {
        if ( dto == null ) {
            return null;
        }

        BankDetails bankDetails = new BankDetails();

        bankDetails.setId( dto.getId() );
        bankDetails.setBankName( dto.getBankName() );
        bankDetails.setAccountHolderName( dto.getAccountHolderName() );
        bankDetails.setAccountType( dto.getAccountType() );
        bankDetails.setBranchCode( dto.getBranchCode() );
        bankDetails.setBranchName( dto.getBranchName() );
        bankDetails.setAccountNumber( dto.getAccountNumber() );

        return bankDetails;
    }
}

