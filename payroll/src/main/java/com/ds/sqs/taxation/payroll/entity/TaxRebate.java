package com.ds.sqs.taxation.payroll.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "tax_rebate", schema = "sars")
@Getter
@Setter
public class TaxRebate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String year;

    @Column(name = "minimum_age", nullable = false)
    private Integer minimumAge;

    @Column(name = "maximum_age", nullable = false)
    private Integer maximumAge;

    @Column(name = "rebate_amount", precision = 10, scale = 2, nullable = false)
    private BigDecimal rebateAmount;

    @Column(name = "rebate_description", nullable = false)
    private String rebateDescription;
}
