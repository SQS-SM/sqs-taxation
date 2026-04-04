package com.ds.sqs.taxation.payroll.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "uif", schema = "sars")

public class Uif {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "effective_from", nullable = false)
    private LocalDate effectiveFrom;

    @Column(name = "effective_to", nullable = false)
    private LocalDate effectiveTo;

    @Column(name = "employee_rate", precision = 5, scale = 4, nullable = false)
    private BigDecimal employeeRate;

    @Column(name = "employer_rate", precision = 5, scale = 4, nullable = false)
    private BigDecimal employerRate;

    @Column(name = "monthly_earnings_cap", precision = 10, scale = 2, nullable = false)
    private BigDecimal monthlyEarningsCap;

    @Column(name = "annual_earnings_cap", precision = 12, scale = 2, nullable = false)
    private BigDecimal annualEarningsCap;

    @Column(name = "active", nullable = false)
    private boolean active;

    /* ===== Constructors ===== */

    public Uif() {
        // JPA only
    }

    /* ===== Getters ===== */

    public int getId() {
        return id;
    }

    public LocalDate getEffectiveFrom() {
        return effectiveFrom;
    }

    public LocalDate getEffectiveTo() {
        return effectiveTo;
    }

    public BigDecimal getEmployeeRate() {
        return employeeRate;
    }

    public BigDecimal getEmployerRate() {
        return employerRate;
    }

    public BigDecimal getMonthlyEarningsCap() {
        return monthlyEarningsCap;
    }

    public BigDecimal getAnnualEarningsCap() {
        return annualEarningsCap;
    }

    public boolean isActive() {
        return active;
    }
}