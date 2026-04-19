package com.ds.sqs.taxation.payroll.mapper;

import com.ds.sqs.taxation.payroll.dto.AllowanceTaxRateDto;
import com.ds.sqs.taxation.payroll.entity.AllowanceTaxRate;
import org.springframework.stereotype.Component;


@Component
public class AllowanceTaxRateMapper {

    public AllowanceTaxRateDto toDto(AllowanceTaxRate entity) {
        if (entity == null) {
            return null;
        }

        AllowanceTaxRateDto allowanceTaxRateDto = new AllowanceTaxRateDto();

        allowanceTaxRateDto.setId(entity.getId());
        allowanceTaxRateDto.setTaxYear(entity.getTaxYear());
        allowanceTaxRateDto.setTaxablePortionPercentage(entity.getTaxablePortionPercentage());
        allowanceTaxRateDto.setEffectiveFrom(entity.getEffectiveFrom());
        allowanceTaxRateDto.setEffectiveTo(entity.getEffectiveTo());
        allowanceTaxRateDto.setActive(entity.isActive());

        return allowanceTaxRateDto;
    }

    public AllowanceTaxRate toEntity(AllowanceTaxRateDto dto) {
        if (dto == null) {
            return null;
        }

        AllowanceTaxRate allowanceTaxRate = new AllowanceTaxRate();

        allowanceTaxRate.setId(dto.getId());
        allowanceTaxRate.setTaxYear(dto.getTaxYear());
        allowanceTaxRate.setTaxablePortionPercentage(dto.getTaxablePortionPercentage());
        allowanceTaxRate.setEffectiveFrom(dto.getEffectiveFrom());
        allowanceTaxRate.setEffectiveTo(dto.getEffectiveTo());
        allowanceTaxRate.setActive(dto.isActive());

        return allowanceTaxRate;
    }
}
