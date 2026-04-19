package com.ds.sqs.taxation.payroll.mapper;

import com.ds.sqs.taxation.payroll.dto.AllowanceTypeDto;
import com.ds.sqs.taxation.payroll.entity.AllowanceType;
import org.springframework.stereotype.Component;

@Component
public class AllowanceTypeMapper {

    public AllowanceTypeDto toDto(AllowanceType entity) {
        if ( entity == null ) {
            return null;
        }

        AllowanceTypeDto allowanceTypeDto = new AllowanceTypeDto();

        allowanceTypeDto.setId( entity.getId() );
        allowanceTypeDto.setAllowanceCode( entity.getAllowanceCode() );
        allowanceTypeDto.setIrp5Code( entity.getIrp5Code() );
        allowanceTypeDto.setDescription( entity.getDescription() );

        return allowanceTypeDto;
    }

    public AllowanceType toEntity(AllowanceTypeDto dto) {
        if ( dto == null ) {
            return null;
        }

        AllowanceType allowanceType = new AllowanceType();

        if ( dto.getId() != null ) {
            allowanceType.setId( dto.getId() );
        }
        allowanceType.setAllowanceCode( dto.getAllowanceCode() );
        allowanceType.setIrp5Code( dto.getIrp5Code() );
        allowanceType.setDescription( dto.getDescription() );

        return allowanceType;
    }
}
