package com.ds.sqs.taxation.payroll.entity;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

@Getter
@Setter
@Entity
@Table(schema = "sars", name = "tax_threshold")
public class TaxThreshold {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "year", nullable = false)
    private String year;

    private Integer minimumAge;
    private Integer maximumAge;

    @Column(name = "threshold_amount", columnDefinition = "money", nullable = false)
    private java.math.BigDecimal thresholdAmount;
}
