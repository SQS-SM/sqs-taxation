package com.ds.sqs.taxation.security.service;

import com.ds.sqs.taxation.security.dto.AccountDto;
import com.ds.sqs.taxation.security.dto.ChangePasswordRequest;
import com.ds.sqs.taxation.security.dto.ForgotPasswordRequest;
import com.ds.sqs.taxation.security.dto.ForgotPasswordResponse;
import com.ds.sqs.taxation.security.dto.LoginRequest;
import com.ds.sqs.taxation.security.dto.LoginResponse;
import com.ds.sqs.taxation.security.dto.RefreshTokenRequest;
import com.ds.sqs.taxation.security.dto.RegisterRequest;
import com.ds.sqs.taxation.security.dto.ResetPasswordRequest;
import com.ds.sqs.taxation.security.dto.SecurityIdentitiesRequest;

import java.util.List;
import java.util.Map;

public interface AccountService {

    AccountDto create(AccountDto dto);

    AccountDto getById(Long id);

    List<AccountDto> findAll();

    List<AccountDto> findByFilters(Long companyId, Long organisationId);

    AccountDto update(Long id, AccountDto dto);

    AccountDto register(RegisterRequest request);

    LoginResponse login(LoginRequest request);

    LoginResponse refreshToken(RefreshTokenRequest request);

    void changePassword(ChangePasswordRequest request);

    ForgotPasswordResponse forgotPassword(ForgotPasswordRequest request);

    void resetPassword(ResetPasswordRequest request);

    Map<String, Object> getSecurityIdentities(SecurityIdentitiesRequest request);

    void delete(Long id);
}
