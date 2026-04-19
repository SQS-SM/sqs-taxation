package com.ds.sqs.taxation.payroll.mapper;

import com.ds.sqs.taxation.payroll.dto.IncomeTaxDto;
import com.ds.sqs.taxation.payroll.entity.IncomeTax;
import org.springframework.stereotype.Component;

@Component
public class IncomeTaxMapper {

    public IncomeTaxDto toDto(IncomeTax entity) {
        if ( entity == null ) {
            return null;
        }

        IncomeTaxDto incomeTaxDto = new IncomeTaxDto();

        incomeTaxDto.setId( entity.getId() );
        incomeTaxDto.setYear( entity.getYear() );
        incomeTaxDto.setMinimumIncome( entity.getMinimumIncome() );
        incomeTaxDto.setMaximumIncome( entity.getMaximumIncome() );
        incomeTaxDto.setTaxPercentage( entity.getTaxPercentage() );

        return incomeTaxDto;
    }

    public IncomeTax toEntity(IncomeTaxDto dto) {
        if ( dto == null ) {
            return null;
        }

        IncomeTax incomeTax = new IncomeTax();

        if ( dto.getId() != null ) {
            incomeTax.setId( dto.getId() );
        }
        incomeTax.setYear( dto.getYear() );
        incomeTax.setMinimumIncome( dto.getMinimumIncome() );
        incomeTax.setMaximumIncome( dto.getMaximumIncome() );
        incomeTax.setTaxPercentage( dto.getTaxPercentage() );

        return incomeTax;
    }
}
