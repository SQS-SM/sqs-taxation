package com.ds.sqs.taxation.payroll.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PensionFundDto {

    private Integer id;
    private String taxYear;
    private BigDecimal annualRemunerationPercent;
    private BigDecimal maximumAmount;
}
