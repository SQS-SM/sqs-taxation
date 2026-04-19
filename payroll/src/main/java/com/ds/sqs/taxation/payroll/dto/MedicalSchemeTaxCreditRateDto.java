package com.ds.sqs.taxation.payroll.dto;

import com.ds.sqs.taxation.payroll.enums.MedicalSchemeTaxCreditType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class MedicalSchemeTaxCreditRateDto {

    @NotNull
    private Long id;
    private Integer taxYear;
    private MedicalSchemeTaxCreditType creditType;
    private BigDecimal monthlyAmount;
}