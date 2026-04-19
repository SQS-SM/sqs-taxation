package com.ds.sqs.taxation.api.utils;

import java.math.BigDecimal;

public final class TaxableIncomeUtils {
    private static final BigDecimal NUMBER_OF_MONTHS_IN_YEAR = new BigDecimal(12);

    public static BigDecimal monthly(BigDecimal annualIncome){
        return annualIncome.divide(NUMBER_OF_MONTHS_IN_YEAR);
    }
    public static BigDecimal annually(BigDecimal monthlyIncome){
        return monthlyIncome.multiply(NUMBER_OF_MONTHS_IN_YEAR);
    }
}
