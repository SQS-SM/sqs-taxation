package com.ds.sqs.taxation.payroll.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Entity
@Table(name = "pension_fund", schema = "sars")

public class PensionFund {
    // --- Getters & Setters ---
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "tax_year", nullable = false, length = 4)
    private String taxYear;

    @Column(name = "annual_remuneration_percent", nullable = false, precision = 5, scale = 2)
    private BigDecimal annualRemunerationPercent;

    @Column(name = "maximum_amount", nullable = false, precision = 12, scale = 2)
    private BigDecimal maximumAmount;

    // --- Constructors ---
    public PensionFund() {
    }

    public PensionFund(String taxYear, BigDecimal annualRemunerationPercent, BigDecimal maximumAmount) {
        this.taxYear = taxYear;
        this.annualRemunerationPercent = annualRemunerationPercent;
        this.maximumAmount = maximumAmount;
    }

    // Optional: toString
    @Override
    public String toString() {
        return "PensionFund{" +
                "id=" + id +
                ", taxYear='" + taxYear + '\'' +
                ", annualRemunerationPercent=" + annualRemunerationPercent +
                ", maximumAmount=" + maximumAmount +
                '}';
    }
}
