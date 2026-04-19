package com.ds.sqs.taxation.payroll.mapper;

import com.ds.sqs.taxation.payroll.dto.PensionFundDto;
import com.ds.sqs.taxation.payroll.entity.PensionFund;
import org.springframework.stereotype.Component;

@Component
public class PensionFundMapper {

    public PensionFundDto toDto(PensionFund entity) {
        if ( entity == null ) {
            return null;
        }

        PensionFundDto pensionFundDto = new PensionFundDto();

        pensionFundDto.setId( entity.getId() );
        pensionFundDto.setTaxYear( entity.getTaxYear() );
        pensionFundDto.setAnnualRemunerationPercent( entity.getAnnualRemunerationPercent() );
        pensionFundDto.setMaximumAmount( entity.getMaximumAmount() );

        return pensionFundDto;
    }

    public PensionFund toEntity(PensionFundDto dto) {
        if ( dto == null ) {
            return null;
        }

        PensionFund pensionFund = new PensionFund();

        if ( dto.getId() != null ) {
            pensionFund.setId( dto.getId() );
        }
        pensionFund.setTaxYear( dto.getTaxYear() );
        pensionFund.setAnnualRemunerationPercent( dto.getAnnualRemunerationPercent() );
        pensionFund.setMaximumAmount( dto.getMaximumAmount() );

        return pensionFund;
    }
}

