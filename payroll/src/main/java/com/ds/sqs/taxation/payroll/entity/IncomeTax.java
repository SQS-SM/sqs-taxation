package com.ds.sqs.taxation.payroll.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "income_tax", schema = "sars")
@Getter
@Setter
public class IncomeTax {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String year;
    @Column(name = "minimum_income", precision = 10, scale = 2, nullable = false)
    private BigDecimal minimumIncome;
    @Column(name = "maximum_income", precision = 10, scale = 2, nullable = false)
    private BigDecimal maximumIncome;
    @Column(name = "sliding_scale", precision = 10, scale = 2, nullable = false)
    private BigDecimal slidingScale;
    @Column(name = "minimum_taxable_amount", precision = 10, scale = 2, nullable = false)
    private BigDecimal minimumTaxableAmount;
    @Column(name = "tax_percentage", precision = 10, scale = 2, nullable = false)
    private BigDecimal taxPercentage;
}
