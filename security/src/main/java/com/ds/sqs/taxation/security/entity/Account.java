package com.ds.sqs.taxation.security.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne(optional = false)
    @JoinColumn(name = "organisation_id")
    private Organisation organisation;

    private String firstName;
    private String lastName;
    private String emailAddress;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private byte[] password;

    private byte[] passwordSalt;

    private UUID passwordResetKey;

    private Boolean isFirstTimeLogin;

    private LocalDateTime createDate;
    private LocalDateTime disableDate;

    @ManyToOne
    @JoinColumn(name = "create_user_id")
    private Account createdBy;

    private UUID guid;

    @PrePersist
    void prePersist() {
        createDate = LocalDateTime.now();
        if (guid == null) {
            guid = UUID.randomUUID();
        }
    }

    // getters and setters
}