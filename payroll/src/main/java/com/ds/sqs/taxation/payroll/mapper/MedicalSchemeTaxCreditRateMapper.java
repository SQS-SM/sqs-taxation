package com.ds.sqs.taxation.payroll.mapper;

import com.ds.sqs.taxation.payroll.dto.MedicalSchemeTaxCreditRateDto;
import com.ds.sqs.taxation.payroll.entity.MedicalSchemeTaxCreditRate;
import org.springframework.stereotype.Component;

@Component
public class MedicalSchemeTaxCreditRateMapper {

    public MedicalSchemeTaxCreditRateDto toDto(MedicalSchemeTaxCreditRate entity) {
        if ( entity == null ) {
            return null;
        }

        MedicalSchemeTaxCreditRateDto medicalSchemeTaxCreditRateDto = new MedicalSchemeTaxCreditRateDto();

        medicalSchemeTaxCreditRateDto.setId( entity.getId() );
        medicalSchemeTaxCreditRateDto.setTaxYear( entity.getTaxYear() );
        medicalSchemeTaxCreditRateDto.setCreditType( entity.getCreditType() );
        medicalSchemeTaxCreditRateDto.setMonthlyAmount( entity.getMonthlyAmount() );

        return medicalSchemeTaxCreditRateDto;
    }

    public MedicalSchemeTaxCreditRate toEntity(MedicalSchemeTaxCreditRateDto dto) {
        if ( dto == null ) {
            return null;
        }

        MedicalSchemeTaxCreditRate medicalSchemeTaxCreditRate = new MedicalSchemeTaxCreditRate();

        medicalSchemeTaxCreditRate.setId( dto.getId() );
        medicalSchemeTaxCreditRate.setTaxYear( dto.getTaxYear() );
        medicalSchemeTaxCreditRate.setCreditType( dto.getCreditType() );
        medicalSchemeTaxCreditRate.setMonthlyAmount( dto.getMonthlyAmount() );

        return medicalSchemeTaxCreditRate;
    }
}