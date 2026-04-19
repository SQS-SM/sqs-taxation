//package com.ds.sqs.taxation.service.service;
//
//import com.ds.sqs.taxation.service.entity.IncomeTax;
//import com.ds.sqs.taxation.service.entity.TaxRebate;
//import com.ds.sqs.taxation.service.repository.IncomeTaxRepository;
//import com.ds.sqs.taxation.service.repository.TaxRebateRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Optional;
//
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import static org.mockito.Mockito.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//@ExtendWith(MockitoExtension.class)
//class IncomeTaxServiceTest {
//
//    @Mock
//    private IncomeTaxRepository incomeTaxRepository;
//
//    @Mock
//    private TaxRebateRepository taxRebateRepository;
//
//    @InjectMocks
//    private IncomeTaxService incomeTaxService;
//
//    @Test
//    void shouldCalculateAnnualIncomeTaxCorrectly() {
//        // given
//        BigDecimal annualIncome = new BigDecimal("300000");
//        IncomeTax tax = getIncomeTax("237100", "42678", "26");
//        TaxRebate primaryRebate = getTaxRebate(0, 65, "17235");
//
//        when(incomeTaxRepository
//                .findByMinimumIncomeLessThanEqualAndMaximumIncomeGreaterThanEqual(
//                        annualIncome, annualIncome))
//                .thenReturn(Optional.of(tax));
//
//        when(taxRebateRepository
//                .findByYear(String.valueOf(LocalDate.now().getYear())))
//                .thenReturn(List.of(primaryRebate));
//
//        // when
//        BigDecimal result = incomeTaxService.calculateAnnualIncomeTax(annualIncome, 35, "2026");
//
//        // then
//        // (300000 - 237100) * 26% = 16354
//        // 42678 + 16354 = 59032
//        // 59032 - 17235 = 41797
//        assertEquals(new BigDecimal("41797.00"), result.setScale(2));
//    }
//
//    @Test
//    void shouldThrowExceptionWhenNoTaxBracketFound() {
//        BigDecimal annualIncome = new BigDecimal("100000");
//
//        when(incomeTaxRepository
//                .findByMinimumIncomeLessThanEqualAndMaximumIncomeGreaterThanEqual(
//                        annualIncome, annualIncome))
//                .thenReturn(Optional.empty());
//
//        IllegalStateException ex = assertThrows(
//                IllegalStateException.class,
//                () -> incomeTaxService.calculateAnnualIncomeTax(annualIncome, 35, "2026")
//        );
//
//        assertTrue(ex.getMessage().contains("No income tax bracket"));
//    }
//
//
//    @Test
//    void shouldCalculateTaxCorrectly() {
//
//        BigDecimal income = new BigDecimal("300000");
//        IncomeTax tax = getIncomeTax("237100", "42678", "26");
//
//        when(incomeTaxRepository
//                .findByMinimumIncomeLessThanEqualAndMaximumIncomeGreaterThanEqual(
//                        income, income))
//                .thenReturn(Optional.of(tax));
//
//        TaxRebate primary = getTaxRebate(0, 64, "16425");
//
//        when(taxRebateRepository.findByYear(anyString()))
//                .thenReturn(List.of(primary));
//
//        BigDecimal result = incomeTaxService.calculateAnnualIncomeTax(income, 35, "2026");
//
//        assertNotNull(result);
//        assertTrue(result.compareTo(BigDecimal.ZERO) > 0);
//    }
//
//
//    @Test
//    void shouldNeverReturnNegativeTax() {
//
//        BigDecimal income = new BigDecimal("50000");
//
//        IncomeTax tax = getIncomeTax("0", "0", "0");
//
//        when(incomeTaxRepository
//                .findByMinimumIncomeLessThanEqualAndMaximumIncomeGreaterThanEqual(
//                        income, income))
//                .thenReturn(Optional.of(tax));
//
//        TaxRebate rebate = getTaxRebate(0, 120, "20000");
//
//        when(taxRebateRepository.findByYear(anyString()))
//                .thenReturn(List.of(rebate));
//
//        BigDecimal result = incomeTaxService.calculateAnnualIncomeTax(income, 40, "2026");
//
//        assertEquals(BigDecimal.ZERO, result);
//    }
//
//    private IncomeTax getIncomeTax(String minimumTaxableAmount, String slidingScale, String taxPercentage) {
//        IncomeTax tax = new IncomeTax();
//
//        tax.setMinimumTaxableAmount(new BigDecimal(minimumTaxableAmount));
//        tax.setSlidingScale(new BigDecimal(slidingScale));
//        tax.setTaxPercentage(new BigDecimal(taxPercentage));
//
//        return tax;
//    }
//
//    private TaxRebate getTaxRebate(Integer minimumAge, Integer maximumAge, String rebateAmount) {
//        TaxRebate rebate = new TaxRebate();
//
//        rebate.setMinimumAge(minimumAge);
//        rebate.setMaximumAge(maximumAge);
//        rebate.setRebateAmount(new BigDecimal(rebateAmount));
//
//        return rebate;
//    }
//
//}