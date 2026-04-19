package com.ds.sqs.taxation.employee.mapper;

import com.ds.sqs.taxation.employee.dto.EmployeePayrollResultDto;
import com.ds.sqs.taxation.employee.entity.Employee;
import com.ds.sqs.taxation.employee.entity.EmployeePayrollResult;
import com.ds.sqs.taxation.employee.entity.PayrollRun;
import org.springframework.stereotype.Component;

@Component
public class EmployeePayrollResultMapper {


    public EmployeePayrollResultDto toDto(EmployeePayrollResult entity) {
        if ( entity == null ) {
            return null;
        }

        EmployeePayrollResultDto employeePayrollResultDto = new EmployeePayrollResultDto();

        employeePayrollResultDto.setPayrollRunId( entityPayrollRunId( entity ) );
        employeePayrollResultDto.setEmployeeId( entityEmployeeId( entity ) );
        employeePayrollResultDto.setId( entity.getId() );
        employeePayrollResultDto.setGrossSalary( entity.getGrossSalary() );
        employeePayrollResultDto.setTaxableIncome( entity.getTaxableIncome() );
        employeePayrollResultDto.setPaye( entity.getPaye() );
        employeePayrollResultDto.setUif( entity.getUif() );
        employeePayrollResultDto.setSdl( entity.getSdl() );
        employeePayrollResultDto.setNetSalary( entity.getNetSalary() );

        return employeePayrollResultDto;
    }


    public EmployeePayrollResult toEntity(EmployeePayrollResultDto dto) {
        if ( dto == null ) {
            return null;
        }

        EmployeePayrollResult employeePayrollResult = new EmployeePayrollResult();

        employeePayrollResult.setId( dto.getId() );
        employeePayrollResult.setGrossSalary( dto.getGrossSalary() );
        employeePayrollResult.setTaxableIncome( dto.getTaxableIncome() );
        employeePayrollResult.setPaye( dto.getPaye() );
        employeePayrollResult.setUif( dto.getUif() );
        employeePayrollResult.setSdl( dto.getSdl() );
        employeePayrollResult.setNetSalary( dto.getNetSalary() );

        return employeePayrollResult;
    }

    private Long entityPayrollRunId(EmployeePayrollResult employeePayrollResult) {
        if ( employeePayrollResult == null ) {
            return null;
        }
        PayrollRun payrollRun = employeePayrollResult.getPayrollRun();
        if ( payrollRun == null ) {
            return null;
        }
        Long id = payrollRun.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long entityEmployeeId(EmployeePayrollResult employeePayrollResult) {
        if ( employeePayrollResult == null ) {
            return null;
        }
        Employee employee = employeePayrollResult.getEmployee();
        if ( employee == null ) {
            return null;
        }
        Long id = employee.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
