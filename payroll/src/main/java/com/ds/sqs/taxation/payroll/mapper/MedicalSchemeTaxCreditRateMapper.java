package com.ds.sqs.taxation.payroll.mapper;

import com.ds.sqs.taxation.payroll.dto.MedicalSchemeTaxCreditRateDto;
import com.ds.sqs.taxation.payroll.entity.MedicalSchemeTaxCreditRate;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MedicalSchemeTaxCreditRateMapper {

    MedicalSchemeTaxCreditRateDto toDto(
            MedicalSchemeTaxCreditRate entity);

    MedicalSchemeTaxCreditRate toEntity(
            MedicalSchemeTaxCreditRateDto dto);
}
