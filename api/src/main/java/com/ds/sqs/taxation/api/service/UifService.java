//package com.ds.sqs.taxation.api.service;
//
//import com.ds.sqs.taxation.api.entity.Uif;
//import com.ds.sqs.taxation.api.repository.UifRepository;
//import com.ds.sqs.taxation.api.utils.TaxableIncomeUtils;
//import org.springframework.stereotype.Service;
//
//import java.math.BigDecimal;
//import java.math.RoundingMode;
//
//@Service
//public class UifService {
//    private final UifRepository uifRepository;
//
//    public UifService(UifRepository uifRepository) {
//        this.uifRepository = uifRepository;
//    }
//
//    public BigDecimal getEmployeeContribution(BigDecimal annualTaxableIncome) {
//        if (annualTaxableIncome == null || annualTaxableIncome.compareTo(BigDecimal.ZERO) < 0) {
//            throw new IllegalArgumentException("Monthly salary must be positive");
//        }
//        Uif uif = uifRepository.findCurrentUIF()
//                .orElseThrow(() -> new IllegalStateException("No active UIF configuration found"));
//
//        BigDecimal monthlyTaxableIncome = TaxableIncomeUtils.monthly(annualTaxableIncome);
//        BigDecimal uifEarnings = monthlyTaxableIncome.min(uif.getMonthlyEarningsCap());
//
//        var employeeUif = uifEarnings
//                .multiply(uif.getEmployerRate());
//
//        return TaxableIncomeUtils.annually(employeeUif);
//    }
//
//    public BigDecimal getEmployerContribution(BigDecimal annualTaxableIncome) {
//        if (annualTaxableIncome == null || annualTaxableIncome.compareTo(BigDecimal.ZERO) < 0) {
//            throw new IllegalArgumentException("Monthly salary must be positive");
//        }
//
//        BigDecimal monthlyTaxableIncome = TaxableIncomeUtils.monthly(annualTaxableIncome);
//        Uif uif = uifRepository.findCurrentUIF()
//                .orElseThrow(() -> new IllegalStateException("No active UIF configuration found"));
//
//        BigDecimal uifEarnings = monthlyTaxableIncome.min(uif.getMonthlyEarningsCap());
//
//        var employerUif = uifEarnings
//                .multiply(uif.getEmployerRate())
//                .setScale(2, RoundingMode.HALF_UP);
//
//        return TaxableIncomeUtils.monthly(employerUif);
//    }
//}
