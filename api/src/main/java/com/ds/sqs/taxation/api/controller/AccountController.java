package com.ds.sqs.taxation.api.controller;

import com.ds.sqs.taxation.security.dto.*;
import com.ds.sqs.taxation.security.service.AccountService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/accounts")
@Tag(name = "Account Management", description = "APIs for managing user accounts, authentication and security")
public class AccountController {

    private final AccountService service;


    @Operation(
            summary = "Retrieve accounts",
            description = "Returns a list of accounts optionally filtered by companyId or organizationId"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Accounts retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid filter parameters"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<List<AccountDto>> getAll(

            @Parameter(description = "Filter by company ID", example = "1001")
            @RequestParam(value = "companyId", required = false) Long companyId,

            @Parameter(description = "Filter by organization ID", example = "200")
            @RequestParam(value = "organizationId", required = false) Long organizationId
    ) {
        return ResponseEntity.ok(service.findByFilters(companyId, organizationId));
    }


    @Operation(
            summary = "Get account by ID",
            description = "Retrieve account details using account identifier"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Account retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Account not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getById(

            @Parameter(description = "Unique account identifier", example = "1")
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(service.getById(id));
    }


    @Operation(
            summary = "Create account",
            description = "Creates a new account record"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Account created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid account data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<AccountDto> create(@RequestBody AccountDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }


    @Operation(
            summary = "Register new user",
            description = "Registers a new user account with login credentials"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "User registered successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid registration data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/register")
    public ResponseEntity<AccountDto> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.register(request));
    }


    @Operation(
            summary = "User login",
            description = "Authenticates a user and returns access and refresh tokens"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login successful"),
            @ApiResponse(responseCode = "401", description = "Invalid credentials"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(service.login(request));
    }


    @Operation(
            summary = "Refresh authentication token",
            description = "Generates a new access token using a valid refresh token"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Token refreshed successfully"),
            @ApiResponse(responseCode = "401", description = "Invalid refresh token"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/token/refresh")
    public ResponseEntity<LoginResponse> refreshToken(@RequestBody RefreshTokenRequest request) {
        return ResponseEntity.ok(service.refreshToken(request));
    }


    @Operation(
            summary = "Change password",
            description = "Allows authenticated users to change their password"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Password changed successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid password request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/change-password")
    public ResponseEntity<Map<String, String>> changePassword(@RequestBody ChangePasswordRequest request) {

        service.changePassword(request);

        return ResponseEntity.ok(Map.of("message", "Password changed successfully"));
    }


    @Operation(
            summary = "Forgot password",
            description = "Initiates password recovery process"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Password recovery initiated"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/forgot-password")
    public ResponseEntity<ForgotPasswordResponse> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        return ResponseEntity.ok(service.forgotPassword(request));
    }


    @Operation(
            summary = "Reset password",
            description = "Resets password using a valid reset token"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Password reset successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid reset request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/reset-password")
    public ResponseEntity<Map<String, String>> resetPassword(@RequestBody ResetPasswordRequest request) {

        service.resetPassword(request);

        return ResponseEntity.ok(Map.of("message", "Password reset successfully"));
    }


    @Operation(
            summary = "Retrieve security identities",
            description = "Returns security identity information based on provided criteria"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Security identities retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/security-identities")
    public ResponseEntity<Map<String, Object>> securityIdentities(@RequestBody SecurityIdentitiesRequest request) {
        return ResponseEntity.ok(service.getSecurityIdentities(request));
    }


    @Operation(
            summary = "Update account",
            description = "Updates account information using account ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Account updated successfully"),
            @ApiResponse(responseCode = "404", description = "Account not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{id}")
    public ResponseEntity<AccountDto> update(

            @Parameter(description = "Unique account identifier", example = "1")
            @PathVariable("id") Long id,

            @RequestBody AccountDto dto
    ) {
        return ResponseEntity.ok(service.update(id, dto));
    }


    @Operation(
            summary = "Delete account",
            description = "Deletes an account by its identifier"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Account deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Account not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(

            @Parameter(description = "Unique account identifier", example = "1")
            @PathVariable("id") Long id
    ) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}