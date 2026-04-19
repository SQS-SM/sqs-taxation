//package com.ds.sqs.taxation.api.service;
//
//import com.ds.sqs.taxation.api.entity.PensionFund;
//import com.ds.sqs.taxation.api.model.IncomeTaxRequest;
//import com.ds.sqs.taxation.api.repository.PensionFundRepository;
//import org.springframework.stereotype.Service;
//
//import java.math.BigDecimal;
//
//@Service
//public class PensionService {
//    private final PensionFundRepository pensionFundRepository;
//
//    public PensionService(PensionFundRepository pensionFundRepository) {
//        this.pensionFundRepository = pensionFundRepository;
//    }
//
//    public BigDecimal getTaxFreePensionAmount(IncomeTaxRequest incomeTaxRequest) {
//        if (null == incomeTaxRequest.getPensionContribution() || BigDecimal.ZERO.equals(incomeTaxRequest.getPensionContribution())) {
//            return BigDecimal.ZERO;
//        }
//
//        PensionFund pensionFund = pensionFundRepository.findByTaxYear(incomeTaxRequest.getTaxYear())
//                .orElseThrow(() ->
//                        new IllegalStateException("No income tax bracket found for tax year: " + incomeTaxRequest.getTaxYear())
//                );
//
//        BigDecimal salaryTaxFreePensionMaximumAmount = incomeTaxRequest.getGrossSalary().multiply(pensionFund.getAnnualRemunerationPercent().divide(new BigDecimal(100)));
//        BigDecimal taxFreePensionMaximumAmount = pensionFund.getMaximumAmount();
//
//        return salaryTaxFreePensionMaximumAmount.min(taxFreePensionMaximumAmount.min(incomeTaxRequest.getPensionContribution()));
//    }
//}
