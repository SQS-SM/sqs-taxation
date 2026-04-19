package com.ds.sqs.taxation.payroll.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TaxRebateDto {

    private Integer id;
    private String year;
    private Integer minimumAge;
    private Integer maximumAge;
    private BigDecimal rebateAmount;
    private String rebateDescription;
}
