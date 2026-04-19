package com.ds.sqs.taxation.api.enums;

public enum TaxPeriod {
    MONTHLY(1),
    ANNUALLY(12);

    private final int months;

    TaxPeriod(int months){
        this.months = months;
    }

    public int getMonths(){
        return months;
    }
}
