package com.ds.sqs.taxation.payroll.mapper;

import com.ds.sqs.taxation.payroll.dto.TaxRebateDto;
import com.ds.sqs.taxation.payroll.entity.TaxRebate;
import org.springframework.stereotype.Component;

@Component
public class TaxRebateMapper {


    public TaxRebateDto toDto(TaxRebate entity) {
        if ( entity == null ) {
            return null;
        }

        TaxRebateDto taxRebateDto = new TaxRebateDto();

        taxRebateDto.setId( entity.getId() );
        taxRebateDto.setYear( entity.getYear() );
        taxRebateDto.setMinimumAge( entity.getMinimumAge() );
        taxRebateDto.setMaximumAge( entity.getMaximumAge() );
        taxRebateDto.setRebateAmount( entity.getRebateAmount() );
        taxRebateDto.setRebateDescription( entity.getRebateDescription() );

        return taxRebateDto;
    }

    public TaxRebate toEntity(TaxRebateDto dto) {
        if ( dto == null ) {
            return null;
        }

        TaxRebate taxRebate = new TaxRebate();

        if ( dto.getId() != null ) {
            taxRebate.setId( dto.getId() );
        }
        taxRebate.setYear( dto.getYear() );
        taxRebate.setMinimumAge( dto.getMinimumAge() );
        taxRebate.setMaximumAge( dto.getMaximumAge() );
        taxRebate.setRebateAmount( dto.getRebateAmount() );
        taxRebate.setRebateDescription( dto.getRebateDescription() );

        return taxRebate;
    }
}

