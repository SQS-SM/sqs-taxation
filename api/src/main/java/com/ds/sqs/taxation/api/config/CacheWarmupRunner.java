package com.ds.sqs.taxation.api.config;

import com.ds.sqs.taxation.api.service.CountryService;
import com.ds.sqs.taxation.api.service.ProvinceService;
import com.ds.sqs.taxation.payroll.adapter.AllowanceTaxRatePersistencePort;
import com.ds.sqs.taxation.payroll.adapter.AllowanceTypePersistencePort;
import com.ds.sqs.taxation.payroll.adapter.BenefitPersistencePort;
import com.ds.sqs.taxation.payroll.adapter.IncomeTaxPersistencePort;
import com.ds.sqs.taxation.payroll.adapter.MedicalSchemeTaxCreditRatePersistencePort;
import com.ds.sqs.taxation.payroll.adapter.PensionFundPersistencePort;
import com.ds.sqs.taxation.payroll.adapter.TaxRebatePersistencePort;
import com.ds.sqs.taxation.payroll.adapter.TaxThresholdPersistencePort;
import com.ds.sqs.taxation.payroll.adapter.UifPersistencePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CacheWarmupRunner implements ApplicationRunner {

    private final CountryService countryService;
    private final ProvinceService provinceService;
    private final IncomeTaxPersistencePort incomeTaxPort;
    private final TaxRebatePersistencePort taxRebatePort;
    private final TaxThresholdPersistencePort taxThresholdPort;
    private final UifPersistencePort uifPort;
    private final MedicalSchemeTaxCreditRatePersistencePort medicalSchemeTaxCreditRatePort;
    private final AllowanceTaxRatePersistencePort allowanceTaxRatePort;
    private final AllowanceTypePersistencePort allowanceTypePort;
    private final BenefitPersistencePort benefitPort;
    private final PensionFundPersistencePort pensionFundPort;

    @Value("${app.cache.warmup.enabled:true}")
    private boolean cacheWarmupEnabled;

    @Override
    public void run(ApplicationArguments args) {
        if (!cacheWarmupEnabled) {
            log.info("Cache warmup disabled");
            return;
        }

        log.info("Starting cache warmup");

        warm("countries", () -> countryService.findAll().size());
        warm("provinces", () -> provinceService.findAll().size());

        warm("income tax", () -> incomeTaxPort.findAll().size());
        warm("tax rebates", () -> taxRebatePort.findAll().size());
        warm("tax thresholds", () -> taxThresholdPort.findAll().size());
        warm("uif", () -> uifPort.findAll().size());
        warm("uif current", () -> uifPort.findCurrent().isPresent() ? 1 : 0);
        warm("medical scheme tax credit rates", () -> medicalSchemeTaxCreditRatePort.findAll().size());
        warm("allowance tax rates", () -> allowanceTaxRatePort.findAll().size());
        warm("allowance types", () -> allowanceTypePort.findAll().size());
        warm("benefits", () -> benefitPort.findAll().size());
        warm("pension funds", () -> pensionFundPort.findAll().size());

        log.info("Cache warmup completed");
    }

    private void warm(String label, Loader loader) {
        try {
            int count = loader.loadCount();
            log.info("Warmup loaded {} records for {}", count, label);
        } catch (Exception ex) {
            log.warn("Warmup failed for {}", label, ex);
        }
    }

    @FunctionalInterface
    interface Loader {
        int loadCount();
    }
}
