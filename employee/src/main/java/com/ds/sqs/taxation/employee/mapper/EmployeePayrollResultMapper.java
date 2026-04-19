package com.ds.sqs.taxation.employee.mapper;

import com.ds.sqs.taxation.employee.dto.EmployeePayrollResultDto;
import com.ds.sqs.taxation.employee.entity.EmployeePayrollResult;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeePayrollResultMapper {

    @Mapping(source = "payrollRun.id", target = "payrollRunId")
    @Mapping(source = "employee.id", target = "employeeId")
    EmployeePayrollResultDto toDto(EmployeePayrollResult entity);

    @Mapping(target = "payrollRun", ignore = true)
    @Mapping(target = "employee", ignore = true)
    EmployeePayrollResult toEntity(EmployeePayrollResultDto dto);
}
