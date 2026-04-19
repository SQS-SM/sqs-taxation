package com.ds.sqs.taxation.employee.mapper;

import com.ds.sqs.taxation.employee.dto.PayrollRunDto;
import com.ds.sqs.taxation.employee.entity.PayrollRun;
import com.ds.sqs.taxation.security.entity.Company;
import org.springframework.stereotype.Component;

@Component
public class PayrollRunMapper {


    public PayrollRunDto toDto(PayrollRun entity) {
        if ( entity == null ) {
            return null;
        }

        PayrollRunDto payrollRunDto = new PayrollRunDto();

        payrollRunDto.setCompanyId( entityCompanyId( entity ) );
        payrollRunDto.setId( entity.getId() );
        payrollRunDto.setTaxYear( entity.getTaxYear() );
        payrollRunDto.setPeriod( entity.getPeriod() );
        payrollRunDto.setProcessedAt( entity.getProcessedAt() );

        return payrollRunDto;
    }


    public PayrollRun toEntity(PayrollRunDto dto) {
        if ( dto == null ) {
            return null;
        }

        PayrollRun payrollRun = new PayrollRun();

        payrollRun.setId( dto.getId() );
        payrollRun.setTaxYear( dto.getTaxYear() );
        payrollRun.setPeriod( dto.getPeriod() );
        payrollRun.setProcessedAt( dto.getProcessedAt() );

        return payrollRun;
    }

    private Long entityCompanyId(PayrollRun payrollRun) {
        if ( payrollRun == null ) {
            return null;
        }
        Company company = payrollRun.getCompany();
        if ( company == null ) {
            return null;
        }
        Long id = company.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
