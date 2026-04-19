package com.ds.sqs.taxation.api.model;

//import com.ds.sqs.taxation.api.enums.AllowanceCode;
import com.ds.sqs.taxation.api.enums.TaxPeriod;
import com.ds.sqs.taxation.api.utils.TaxableIncomeUtils;
import lombok.Data;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Data
public class IncomeTaxRequest {
    private int age;
    private String taxYear;
    private int numberOfDependants;
    private BigDecimal grossSalary;
    private BigDecimal pensionContribution;
    private TaxPeriod taxPeriod;
    private BigDecimal medicalAidContribution;
//    private HashMap<AllowanceCode, BigDecimal> allowances = new HashMap<>();
    private HashMap<String, BigDecimal> benefits = new HashMap<>();

    public void toAnnual(){
        if(TaxPeriod.ANNUALLY.equals(taxPeriod)){
            return;
        }

        grossSalary = TaxableIncomeUtils.annually(grossSalary);
        pensionContribution = TaxableIncomeUtils.annually(pensionContribution);
        medicalAidContribution = TaxableIncomeUtils.annually(medicalAidContribution);

//        for (Map.Entry<AllowanceCode, BigDecimal> entry : allowances.entrySet()) {
//            entry.setValue(
//                    TaxableIncomeUtils.annually(entry.getValue())
//            );
//        }

    }
}
