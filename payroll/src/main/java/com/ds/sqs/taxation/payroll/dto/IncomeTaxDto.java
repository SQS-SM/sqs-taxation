package com.ds.sqs.taxation.payroll.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class IncomeTaxDto {

    private Integer id;
    private String year;
    private BigDecimal minimumIncome;
    private BigDecimal maximumIncome;
    private BigDecimal taxPercentage;
}
