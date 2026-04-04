package com.ds.sqs.taxation.api.controller;

import com.ds.sqs.taxation.payroll.adapter.*;
import com.ds.sqs.taxation.payroll.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tax")
@Tag(name = "Tax Tables", description = "Lookup endpoints for tax reference tables")
public class TaxTablesController {

    private final IncomeTaxPersistencePort incomeTaxPort;
    private final TaxThresholdPersistencePort taxThresholdPort;
    private final TaxRebatePersistencePort taxRebatePort;
    private final PensionFundPersistencePort pensionFundPort;
    private final AllowanceTypePersistencePort allowanceTypePort;
    private final AllowanceTaxRatePersistencePort allowanceTaxRatePort;
    private final UifPersistencePort uifPort;
    private final MedicalSchemeTaxCreditRatePersistencePort medicalSchemePort;
    private final BenefitPersistencePort benefitPort;

    @Operation(summary = "List income taxes")
    @GetMapping("/income-taxes")
    public ResponseEntity<List<IncomeTaxDto>> getIncomeTaxes() {
        return ResponseEntity.ok(incomeTaxPort.findAll());
    }

    @Operation(summary = "List tax thresholds")
    @GetMapping("/tax-thresholds")
    public ResponseEntity<List<com.ds.sqs.taxation.payroll.dto.TaxThresholdDto>> getTaxThresholds() {
        return ResponseEntity.ok(taxThresholdPort.findAll());
    }

    @Operation(summary = "List tax rebates")
    @GetMapping("/tax-rebates")
    public ResponseEntity<List<com.ds.sqs.taxation.payroll.dto.TaxRebateDto>> getTaxRebates() {
        return ResponseEntity.ok(taxRebatePort.findAll());
    }

    @Operation(summary = "List pension funds")
    @GetMapping("/pension-funds")
    public ResponseEntity<List<com.ds.sqs.taxation.payroll.dto.PensionFundDto>> getPensionFunds() {
        return ResponseEntity.ok(pensionFundPort.findAll());
    }

    @Operation(summary = "List allowance types")
    @GetMapping("/allowance-types")
    public ResponseEntity<List<com.ds.sqs.taxation.payroll.dto.AllowanceTypeDto>> getAllowanceTypes() {
        return ResponseEntity.ok(allowanceTypePort.findAll());
    }

    @Operation(summary = "List allowance tax rates")
    @GetMapping("/allowance-tax-rates")
    public ResponseEntity<List<com.ds.sqs.taxation.payroll.dto.AllowanceTaxRateDto>> getAllowanceTaxRates() {
        return ResponseEntity.ok(allowanceTaxRatePort.findAll());
    }

    @Operation(summary = "List UIF rates")
    @GetMapping("/uif")
    public ResponseEntity<List<com.ds.sqs.taxation.payroll.dto.UifDto>> getUifRates() {
        return ResponseEntity.ok(uifPort.findAll());
    }

    @Operation(summary = "List medical scheme tax credits")
    @GetMapping("/medical-scheme-tax-credits")
    public ResponseEntity<List<com.ds.sqs.taxation.payroll.dto.MedicalSchemeTaxCreditRateDto>> getMedicalSchemeTaxCredits() {
        return ResponseEntity.ok(medicalSchemePort.findAll());
    }

    @Operation(summary = "List benefits")
    @GetMapping("/benefits")
    public ResponseEntity<List<com.ds.sqs.taxation.payroll.dto.BenefitDto>> getBenefits() {
        return ResponseEntity.ok(benefitPort.findAll());
    }
}