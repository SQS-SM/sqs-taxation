//package com.ds.sqs.taxation.api.service;
//
//import com.ds.sqs.taxation.api.entity.IncomeTax;
//import com.ds.sqs.taxation.api.enums.TaxPeriod;
//import com.ds.sqs.taxation.api.model.IncomeTaxRequest;
//import com.ds.sqs.taxation.api.model.IncomeTaxResponse;
//import com.ds.sqs.taxation.api.model.SalaryDeductions;
//import com.ds.sqs.taxation.api.repository.IncomeTaxRepository;
//import org.springframework.stereotype.Service;
//
//import java.math.BigDecimal;
//import java.math.RoundingMode;
//import java.util.Optional;
//
//@Service
//public class IncomeTaxService {
//
//    private final IncomeTaxRepository incomeTaxRepository;
//    private final TaxRebateService taxRebateService;
//    private final MedicalSchemeTaxCreditRateService medicalSchemeTaxCreditRateService;
//    private final UifService uifService;
//    private final PensionService pensionService;
//    private final AllowanceService allowanceService;
//
//    public IncomeTaxService(IncomeTaxRepository incomeTaxRepository,
//                            TaxRebateService taxRebateService,
//                            MedicalSchemeTaxCreditRateService medicalSchemeTaxCreditRateService,
//                            UifService uifService,
//                            PensionService pensionService,
//                            AllowanceService allowanceService) {
//        this.incomeTaxRepository = incomeTaxRepository;
//        this.taxRebateService = taxRebateService;
//        this.medicalSchemeTaxCreditRateService = medicalSchemeTaxCreditRateService;
//        this.uifService = uifService;
//        this.pensionService = pensionService;
//        this.allowanceService = allowanceService;
//    }
//
//    public IncomeTaxResponse calculate(IncomeTaxRequest incomeTaxRequest) {
//        if(incomeTaxRequest.getTaxPeriod().equals(TaxPeriod.MONTHLY)){
//            incomeTaxRequest.toAnnual();
//        }
//
//        IncomeTaxResponse response = calculateAnnualIncomeTax( incomeTaxRequest);
//        response.toMonthly(incomeTaxRequest.getTaxPeriod());
//
//        return response;
//    }
//
//    private IncomeTaxResponse calculateAnnualIncomeTax(IncomeTaxRequest incomeTaxRequest) {
//        IncomeTaxResponse response = new IncomeTaxResponse();
//        if (incomeTaxRequest == null ||
//                incomeTaxRequest.getGrossSalary() == null ||
//                incomeTaxRequest.getGrossSalary().signum() <= 0) {
//            throw new IllegalArgumentException("Annual cost to company must be greater than zero");
//        }
//
//        var taxableIncome = getTaxableIncome(incomeTaxRequest);
//
//        var incomeTax = calculateIncomeTax(taxableIncome);
//        var medicalAidTaxCredits = medicalSchemeTaxCreditRateService.getTaxCredits(incomeTaxRequest.getNumberOfDependants(), Integer.parseInt(incomeTaxRequest.getTaxYear()));
//        var taxRebates = taxRebateService.getTaxRebate(incomeTaxRequest);
//        var paye = incomeTax.subtract(taxRebates.add(medicalAidTaxCredits));
//
//        var uifContributions = uifService.getEmployeeContribution(incomeTaxRequest.getGrossSalary());
//
//        SalaryDeductions salaryDeductions = new SalaryDeductions();
//        salaryDeductions.setPayAsYouEarn(paye);
//        salaryDeductions.setMedicalAid(Optional.ofNullable(incomeTaxRequest.getMedicalAidContribution()));
//        salaryDeductions.setPensionFund(Optional.ofNullable(incomeTaxRequest.getPensionContribution()));
//        salaryDeductions.setUnemploymentInsuranceFund(uifContributions);
//
//        var totalDeductions = paye.add(uifContributions).add(incomeTaxRequest.getMedicalAidContribution()).add(incomeTaxRequest.getPensionContribution());
//
//        response.setTotalDeductions(totalDeductions);
//        response.setNetPay(incomeTaxRequest.getGrossSalary().subtract(totalDeductions));
//        response.setDeductions(salaryDeductions);
//
//        return response;
//    }
//
//    private BigDecimal getTaxableIncome(IncomeTaxRequest request) {
//        BigDecimal allowancesTaxFreeAmount =
//            request.getAllowances().entrySet().stream()
//                    .map(entry ->
//                            allowanceService.getAllowanceTaxFreeAmount(
//                                    entry.getKey(),
//                                    request.getTaxYear(),
//                                    entry.getValue()
//                            )
//                    )
//                    .reduce(BigDecimal.ZERO, BigDecimal::add);
//
//        return request.getGrossSalary().subtract(pensionService.getTaxFreePensionAmount(request)).subtract(allowancesTaxFreeAmount);
//    }
//
//    private BigDecimal calculateIncomeTax(BigDecimal taxableIncome) {
//
//        IncomeTax tax =
//                incomeTaxRepository.findByMinimumIncomeLessThanEqualAndMaximumIncomeGreaterThanEqual(
//                                taxableIncome,
//                                taxableIncome
//                        )
//                        .orElseThrow(() ->
//                                new IllegalStateException("No income tax bracket found for income: " + taxableIncome)
//                        );
//
//        var taxableIncomeAboveThreshold = taxableIncome.subtract(tax.getMinimumTaxableAmount());
//
//        if (taxableIncomeAboveThreshold.signum() < 0) {
//            taxableIncomeAboveThreshold = BigDecimal.ZERO;
//        }
//
//        BigDecimal taxRate =
//                tax.getTaxPercentage()
//                        .divide(BigDecimal.valueOf(100), 6, RoundingMode.HALF_UP);
//
//        var aboveThresholdIncomeTax = taxableIncomeAboveThreshold.multiply(taxRate);
//
//        return tax.getSlidingScale().add(aboveThresholdIncomeTax);
//    }
//
//}
