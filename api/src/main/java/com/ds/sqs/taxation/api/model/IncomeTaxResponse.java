package com.ds.sqs.taxation.api.model;

import com.ds.sqs.taxation.api.enums.TaxPeriod;
import com.ds.sqs.taxation.api.utils.TaxableIncomeUtils;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class IncomeTaxResponse {
    private SalaryDeductions deductions;
    private BigDecimal totalDeductions;
    private BigDecimal netPay;

    public void toMonthly(TaxPeriod taxPeriod){
        totalDeductions = TaxableIncomeUtils.monthly(totalDeductions);
        netPay = TaxableIncomeUtils.monthly(netPay);
        deductions.toMonthly(taxPeriod);
    }
}
