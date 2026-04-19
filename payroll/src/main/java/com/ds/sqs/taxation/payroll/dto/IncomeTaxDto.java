package com.ds.sqs.taxation.payroll.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class IncomeTaxDto {

    @NotNull
    private Integer id;
    private String year;
    private BigDecimal minimumIncome;
    private BigDecimal maximumIncome;
    private BigDecimal taxPercentage;
}
