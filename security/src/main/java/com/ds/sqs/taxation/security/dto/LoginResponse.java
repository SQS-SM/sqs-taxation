package com.ds.sqs.taxation.security.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class LoginResponse {

    private String tokenType;
    private String accessToken;
    private String refreshToken;
    private String idToken;
    private Long expiresIn;
    private AccountDto account;
    private List<String> securityIdentities;
}
