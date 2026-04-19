//package com.ds.sqs.taxation.api.service;
//
//import com.ds.sqs.taxation.api.enums.AllowanceCode;
//import com.ds.sqs.taxation.api.repository.AllowanceTaxRateRepository;
//import org.springframework.stereotype.Service;
//
//import java.math.BigDecimal;
//
//@Service
//public class AllowanceService {
//
//    private final AllowanceTaxRateRepository repository;
//
//    public AllowanceService(AllowanceTaxRateRepository repository) {
//        this.repository = repository;
//    }
//
//    public BigDecimal getAllowanceTaxFreePercentage(AllowanceCode code, String year){
//       var allowance = repository.findByAllowanceTypeCodeAndTaxYear(code, year)
//                .orElseThrow(() ->
//                        new IllegalStateException("No income tax bracket found for income: " + code)
//                );
//
//       return new BigDecimal(100).subtract(allowance.getTaxablePortionPercentage());
//    }
//
//    public BigDecimal getAllowanceTaxFreeAmount(AllowanceCode code, String year, BigDecimal annualAllowanceAmount){
//        var taxFreePercentage =getAllowanceTaxFreePercentage(code, year);
//
//        return annualAllowanceAmount.multiply(taxFreePercentage.divide(new BigDecimal(100)));
//    }
//}
