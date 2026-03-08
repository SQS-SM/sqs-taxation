package com.ds.sqs.taxation.security.dto;

import lombok.Data;

@Data
public class RegisterRequest {

    private Long roleId;
    private Long companyId;
    private Long organisationId;

    private String firstName;
    private String lastName;
    private String emailAddress;
    private String username;
    private String password;
}
