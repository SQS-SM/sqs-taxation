package com.ds.sqs.taxation.security.entity;
import com.ds.sqs.taxation.common.entity.Address;
import com.ds.sqs.taxation.common.entity.AuditableEntity;
import com.ds.sqs.taxation.common.entity.BankDetails;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "company")
public class Company extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private UUID guid;

    @ManyToOne(optional = false)
    @JoinColumn(name = "organisation_id")
    private Organisation organisation;

    @Column(name = "name", nullable = false)
    private String name;

    private String registeredName;
    private String tradingName;
    private String natureOfBusiness;
    private String companyRegistrationNumber;
    private String taxNumber;

    @ManyToOne
    @JoinColumn(name = "physical_address_id")
    private Address physicalAddress;

    @ManyToOne
    @JoinColumn(name = "postal_address_id")
    private Address postalAddress;

    @ManyToOne
    @JoinColumn(name = "bank_details_id")
    private BankDetails bankDetails;

    @PrePersist
    void generateGuid() {
        if (guid == null) {
            guid = UUID.randomUUID();
        }
    }

    // getters and setters
}