package com.ds.sqs.taxation.employee.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class EmployeePayrollResultDto {

    private Long id;
    private Long payrollRunId;
    private Long employeeId;
    private BigDecimal grossSalary;
    private BigDecimal taxableIncome;
    private BigDecimal paye;
    private BigDecimal uif;
    private BigDecimal sdl;
    private BigDecimal netSalary;
}
