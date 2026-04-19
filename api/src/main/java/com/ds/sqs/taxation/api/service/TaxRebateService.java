//package com.ds.sqs.taxation.api.service;
//
//import com.ds.sqs.taxation.api.entity.TaxRebate;
//import com.ds.sqs.taxation.api.model.IncomeTaxRequest;
//import com.ds.sqs.taxation.api.repository.TaxRebateRepository;
//import org.springframework.stereotype.Service;
//
//import java.math.BigDecimal;
//import java.util.List;
//
//@Service
//public class TaxRebateService {
//    private final TaxRebateRepository taxRebateRepository;
//
//    public TaxRebateService(TaxRebateRepository taxRebateRepository) {
//        this.taxRebateRepository = taxRebateRepository;
//    }
//
//    public BigDecimal getTaxRebate(IncomeTaxRequest incomeTaxRequest) {
//        List<TaxRebate> rebates = taxRebateRepository.findByYear(incomeTaxRequest.getTaxYear());
//        BigDecimal totalRebate = BigDecimal.ZERO;
//
//        for (TaxRebate rebate : rebates) {
//            if (incomeTaxRequest.getAge() >= rebate.getMinimumAge()) {
//                totalRebate = totalRebate.add(rebate.getRebateAmount());
//            }
//        }
//
//        return totalRebate;
//    }
//}
