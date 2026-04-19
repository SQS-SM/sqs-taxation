package com.ds.sqs.taxation.payroll.mapper;

import com.ds.sqs.taxation.payroll.dto.BenefitDto;
import com.ds.sqs.taxation.payroll.entity.Benefit;
import org.springframework.stereotype.Component;

@Component
public class BenefitMapper {

    public BenefitDto toDto(Benefit entity) {
        if ( entity == null ) {
            return null;
        }

        BenefitDto benefitDto = new BenefitDto();

        benefitDto.setId( entity.getId() );
        benefitDto.setTaxYear( entity.getTaxYear() );
        benefitDto.setCode( entity.getCode() );
        benefitDto.setIrp5Code( entity.getIrp5Code() );
        benefitDto.setName( entity.getName() );
        benefitDto.setTaxable( entity.getTaxable() );

        return benefitDto;
    }

    public Benefit toEntity(BenefitDto dto) {
        if ( dto == null ) {
            return null;
        }

        Benefit benefit = new Benefit();

        benefit.setId( dto.getId() );
        benefit.setTaxYear( dto.getTaxYear() );
        benefit.setCode( dto.getCode() );
        benefit.setIrp5Code( dto.getIrp5Code() );
        benefit.setName( dto.getName() );
        benefit.setTaxable( dto.getTaxable() );

        return benefit;
    }
}
