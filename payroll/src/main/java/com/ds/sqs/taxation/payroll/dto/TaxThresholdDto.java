package com.ds.sqs.taxation.payroll.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TaxThresholdDto {

    private Integer id;
    private String year;
    private Integer minimumAge;
    private Integer maximumAge;
    private BigDecimal thresholdAmount;
}
