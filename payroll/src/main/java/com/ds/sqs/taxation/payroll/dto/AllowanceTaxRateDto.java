package com.ds.sqs.taxation.payroll.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class AllowanceTaxRateDto {

    private Long id;
    private Integer allowanceTypeId;
    private String taxYear;
    private BigDecimal taxablePortionPercentage;
    private LocalDate effectiveFrom;
    private LocalDate effectiveTo;
    private boolean active;
}
