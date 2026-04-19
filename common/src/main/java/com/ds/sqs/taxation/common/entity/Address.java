package com.ds.sqs.taxation.common.entity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "address")
public class Address extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String line1;
    private String line2;
    private String suburb;
    private String city;
    private String postalCode;

    @ManyToOne
    @JoinColumn(name = "province_id")
    private Province province;
}