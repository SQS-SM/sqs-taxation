package com.ds.sqs.taxation.payroll.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class UifDto {

    private Integer id;
    private LocalDate effectiveFrom;
    private LocalDate effectiveTo;
    private BigDecimal employeeRate;
    private BigDecimal employerRate;
    private BigDecimal monthlyEarningsCap;
    private BigDecimal annualEarningsCap;
    private Boolean active;
}
