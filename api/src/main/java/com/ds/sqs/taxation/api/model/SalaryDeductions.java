package com.ds.sqs.taxation.api.model;

import com.ds.sqs.taxation.api.enums.TaxPeriod;
import com.ds.sqs.taxation.api.utils.TaxableIncomeUtils;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Optional;

@Data
public class SalaryDeductions {
    private BigDecimal payAsYouEarn;
    private BigDecimal unemploymentInsuranceFund;
    private Optional<BigDecimal> medicalAid;
    private Optional<BigDecimal> pensionFund;

    public void toMonthly(TaxPeriod taxPeriod){
        payAsYouEarn = TaxableIncomeUtils.monthly(payAsYouEarn);
        unemploymentInsuranceFund = TaxableIncomeUtils.monthly(unemploymentInsuranceFund);
        medicalAid = medicalAid.map(value -> Optional.of(TaxableIncomeUtils.monthly(value))).orElse(null);
        pensionFund = pensionFund.map(value -> Optional.of(TaxableIncomeUtils.monthly(value))).orElse(null);
    }
}
