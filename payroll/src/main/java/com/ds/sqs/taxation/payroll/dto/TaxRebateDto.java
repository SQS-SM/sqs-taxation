package com.ds.sqs.taxation.payroll.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TaxRebateDto {

    @NotNull
    private Integer id;
    private String year;
    private Integer minimumAge;
    private Integer maximumAge;
    private BigDecimal rebateAmount;
    private String rebateDescription;
}
