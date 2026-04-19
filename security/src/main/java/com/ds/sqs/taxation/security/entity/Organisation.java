package com.ds.sqs.taxation.security.entity;
import com.ds.sqs.taxation.common.entity.AuditableEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "organisation")
public class Organisation extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private UUID guid;

    @Column(name = "name", nullable = false)
    private String name;

    @PrePersist
    void generateGuid() {
        if (guid == null) {
            guid = UUID.randomUUID();
        }
    }

    // getters and setters
}
