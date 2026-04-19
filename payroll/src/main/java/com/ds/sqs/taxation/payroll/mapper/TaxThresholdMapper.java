package com.ds.sqs.taxation.payroll.mapper;

import com.ds.sqs.taxation.payroll.dto.TaxThresholdDto;
import com.ds.sqs.taxation.payroll.entity.TaxThreshold;
import org.springframework.stereotype.Component;

@Component
public class  TaxThresholdMapper {

    public TaxThresholdDto toDto(TaxThreshold entity) {
        if ( entity == null ) {
            return null;
        }

        TaxThresholdDto taxThresholdDto = new TaxThresholdDto();

        taxThresholdDto.setId( entity.getId() );
        taxThresholdDto.setYear( entity.getYear() );
        taxThresholdDto.setMinimumAge( entity.getMinimumAge() );
        taxThresholdDto.setMaximumAge( entity.getMaximumAge() );
        taxThresholdDto.setThresholdAmount( entity.getThresholdAmount() );

        return taxThresholdDto;
    }

    public TaxThreshold toEntity(TaxThresholdDto dto) {
        if ( dto == null ) {
            return null;
        }

        TaxThreshold taxThreshold = new TaxThreshold();

        taxThreshold.setId( dto.getId() );
        taxThreshold.setYear( dto.getYear() );
        taxThreshold.setMinimumAge( dto.getMinimumAge() );
        taxThreshold.setMaximumAge( dto.getMaximumAge() );
        taxThreshold.setThresholdAmount( dto.getThresholdAmount() );

        return taxThreshold;
    }
}

