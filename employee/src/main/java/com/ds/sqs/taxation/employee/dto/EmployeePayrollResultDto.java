package com.ds.sqs.taxation.employee.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class EmployeePayrollResultDto {

    @NotNull
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
