package com.ds.sqs.taxation.security.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class AccountDto {

    private Long id;

    private Long roleId;
    private Long companyId;
    private Long organisationId;

    private String firstName;
    private String lastName;
    private String emailAddress;
    private String username;

    private Boolean isFirstTimeLogin;

    private LocalDateTime createDate;
    private LocalDateTime disableDate;

    private UUID guid;
}
