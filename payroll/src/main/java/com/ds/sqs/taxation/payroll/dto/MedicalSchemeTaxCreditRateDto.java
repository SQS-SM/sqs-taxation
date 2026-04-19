package com.ds.sqs.taxation.payroll.dto;

import com.ds.sqs.taxation.payroll.enums.MedicalSchemeTaxCreditType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class MedicalSchemeTaxCreditRateDto {

    private Long id;
    private Integer taxYear;
    private MedicalSchemeTaxCreditType creditType;
    private BigDecimal monthlyAmount;
}