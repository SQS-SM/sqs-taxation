package com.ds.sqs.taxation.payroll.entity;


import com.ds.sqs.taxation.payroll.enums.MedicalSchemeTaxCreditType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "medical_scheme_tax_credit_rate", schema = "sars")
@Getter
@Setter
public class MedicalSchemeTaxCreditRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tax_year", nullable = false)
    private Integer taxYear;

    @Enumerated(EnumType.STRING)
    @Column(name = "credit_type_code", nullable = false)
    private MedicalSchemeTaxCreditType creditType;

    @Column(name = "monthly_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal monthlyAmount;

    @Column(name = "effective_from", nullable = false)
    private LocalDate effectiveFrom;

    @Column(name = "effective_to")
    private LocalDate effectiveTo;
}

// getters & setters
