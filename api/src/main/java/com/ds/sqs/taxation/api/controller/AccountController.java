package com.ds.sqs.taxation.api.controller;

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
import com.ds.sqs.taxation.security.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/accounts")
public class AccountController {

    private final AccountService service;

    @GetMapping
    public ResponseEntity<List<AccountDto>> getAll(
            @RequestParam(value = "companyId", required = false) Long companyId,
            @RequestParam(value = "organizationId", required = false) Long organizationId
    ) {
        return ResponseEntity.ok(service.findByFilters(companyId, organizationId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<AccountDto> create(@RequestBody AccountDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PostMapping("/register")
    public ResponseEntity<AccountDto> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(service.login(request));
    }

    @PostMapping("/token/refresh")
    public ResponseEntity<LoginResponse> refreshToken(@RequestBody RefreshTokenRequest request) {
        return ResponseEntity.ok(service.refreshToken(request));
    }

    @PostMapping("/change-password")
    public ResponseEntity<Map<String, String>> changePassword(@RequestBody ChangePasswordRequest request) {
        service.changePassword(request);
        return ResponseEntity.ok(Map.of("message", "Password changed successfully"));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<ForgotPasswordResponse> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        return ResponseEntity.ok(service.forgotPassword(request));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<Map<String, String>> resetPassword(@RequestBody ResetPasswordRequest request) {
        service.resetPassword(request);
        return ResponseEntity.ok(Map.of("message", "Password reset successfully"));
    }

    @PostMapping("/security-identities")
    public ResponseEntity<Map<String, Object>> securityIdentities(@RequestBody SecurityIdentitiesRequest request) {
        return ResponseEntity.ok(service.getSecurityIdentities(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountDto> update(
            @PathVariable("id") Long id,
            @RequestBody AccountDto dto
    ) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
