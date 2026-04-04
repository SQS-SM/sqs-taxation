package com.ds.sqs.taxation.payroll.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "allowance_tax_rate", schema = "sars")
@Getter
@Setter
public class AllowanceTaxRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "allowance_type_id", nullable = false)
    private AllowanceType allowanceType;

    @Column(nullable = false, length = 4)
    private String taxYear;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal taxablePortionPercentage;

    @Column(nullable = false)
    private LocalDate effectiveFrom;

    private LocalDate effectiveTo;

    private boolean active;
}
