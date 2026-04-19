package com.ds.sqs.taxation.payroll.mapper;

import com.ds.sqs.taxation.payroll.dto.UifDto;
import com.ds.sqs.taxation.payroll.entity.Uif;
import org.springframework.stereotype.Component;

@Component
public class UifMapper {

    public UifDto toDto(Uif entity) {
        if ( entity == null ) {
            return null;
        }

        UifDto uifDto = new UifDto();

        uifDto.setId( entity.getId() );
        uifDto.setEffectiveFrom( entity.getEffectiveFrom() );
        uifDto.setEffectiveTo( entity.getEffectiveTo() );
        uifDto.setEmployeeRate( entity.getEmployeeRate() );
        uifDto.setEmployerRate( entity.getEmployerRate() );
        uifDto.setMonthlyEarningsCap( entity.getMonthlyEarningsCap() );
        uifDto.setAnnualEarningsCap( entity.getAnnualEarningsCap() );
        uifDto.setActive( entity.isActive() );

        return uifDto;
    }

    public Uif toEntity(UifDto dto) {
        if ( dto == null ) {
            return null;
        }

        Uif uif = new Uif();

        return uif;
    }
}
