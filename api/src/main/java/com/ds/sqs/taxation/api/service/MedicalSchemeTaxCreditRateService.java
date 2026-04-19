//package com.ds.sqs.taxation.api.service;
//
//import com.ds.sqs.taxation.api.enums.MedicalSchemeTaxCreditType;
//import com.ds.sqs.taxation.api.repository.MedicalSchemeTaxCreditRateRepository;
//import org.springframework.stereotype.Service;
//
//import java.math.BigDecimal;
//
//@Service
//public class MedicalSchemeTaxCreditRateService {
//    public static final int MONTHS_IN_A_YEAR = 12;
//    private final MedicalSchemeTaxCreditRateRepository repository;
//
//    public MedicalSchemeTaxCreditRateService(MedicalSchemeTaxCreditRateRepository repository) {
//        this.repository = repository;
//    }
//
//    public BigDecimal getTaxCredits(int numberOfDependants, int taxYear) {
//        if (numberOfDependants <= 0) {
//            return BigDecimal.ZERO;
//        }
//
//        BigDecimal total = BigDecimal.ZERO;
//
//        total = total.add(
//                rate(taxYear, MedicalSchemeTaxCreditType.PRIMARY_MEMBER)
//        );
//
//        if (numberOfDependants >= 2) {
//            total = total.add(
//                    rate(taxYear, MedicalSchemeTaxCreditType.PRIMARY_AND_FIRST_DEPENDANT)
//            );
//        }
//
//        if (numberOfDependants > 2) {
//            BigDecimal additional =
//                    rate(taxYear, MedicalSchemeTaxCreditType.ADDITIONAL_DEPENDANT)
//                            .multiply(BigDecimal.valueOf(numberOfDependants - 1));
//
//            total = total.add(additional);
//        }
//
//        return total.multiply(new BigDecimal(MONTHS_IN_A_YEAR));
//    }
//
//    private BigDecimal rate(int year, MedicalSchemeTaxCreditType type) {
//        return repository
//                .findByTaxYearAndCreditType(year, type.name())
//                .orElseThrow()
//                .getMonthlyAmount();
//    }
//
//}
