package com.ds.sqs.taxation.security.service.impl;

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
import com.ds.sqs.taxation.security.entity.Account;
import com.ds.sqs.taxation.security.entity.Company;
import com.ds.sqs.taxation.security.entity.Organisation;
import com.ds.sqs.taxation.security.entity.Role;
import com.ds.sqs.taxation.security.mapper.AccountMapper;
import com.ds.sqs.taxation.security.repository.AccountRepository;
import com.ds.sqs.taxation.security.repository.CompanyRepository;
import com.ds.sqs.taxation.security.repository.OrganisationRepository;
import com.ds.sqs.taxation.security.repository.RoleRepository;
import com.ds.sqs.taxation.security.service.AccountService;
import com.ds.sqs.taxation.security.service.JwtService;
import com.ds.sqs.taxation.security.service.PasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final CompanyRepository companyRepository;
    private final OrganisationRepository organisationRepository;
    private final AccountMapper mapper;
    private final PasswordService passwordService;
    private final JwtService jwtService;

    @Override
    public AccountDto create(AccountDto dto) {
        Account account = mapper.toEntity(dto);
        hydrateRelations(account, dto);
        if (account.getPassword() == null) {
            account.setPassword(new byte[0]);
        }
        return mapper.toDto(accountRepository.save(account));
    }

    @Override
    public AccountDto getById(Long id) {
        return accountRepository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    @Override
    public List<AccountDto> findAll() {
        return accountRepository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public List<AccountDto> findByFilters(Long companyId, Long organisationId) {
        if (companyId != null && organisationId != null) {
            return accountRepository.findByCompanyIdAndOrganisationId(companyId, organisationId).stream()
                    .map(mapper::toDto)
                    .toList();
        }
        if (companyId != null) {
            return accountRepository.findByCompanyId(companyId).stream()
                    .map(mapper::toDto)
                    .toList();
        }
        if (organisationId != null) {
            return accountRepository.findByOrganisationId(organisationId).stream()
                    .map(mapper::toDto)
                    .toList();
        }
        return findAll();
    }

    @Override
    public AccountDto update(Long id, AccountDto dto) {
        Account existing = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        Account account = mapper.toEntity(dto);
        account.setId(id);
        account.setGuid(existing.getGuid());
        account.setCreateDate(existing.getCreateDate());
        account.setPassword(existing.getPassword());
        account.setPasswordSalt(existing.getPasswordSalt());
        account.setCreatedBy(existing.getCreatedBy());

        hydrateRelations(account, dto);
        return mapper.toDto(accountRepository.save(account));
    }

    @Override
    public AccountDto register(RegisterRequest request) {
        if (request.getUsername() == null || request.getUsername().isBlank()) {
            throw new RuntimeException("Username is required");
        }
        if (accountRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        AccountDto dto = new AccountDto();
        dto.setRoleId(request.getRoleId());
        dto.setCompanyId(request.getCompanyId());
        dto.setOrganisationId(request.getOrganisationId());
        dto.setFirstName(request.getFirstName());
        dto.setLastName(request.getLastName());
        dto.setEmailAddress(request.getEmailAddress());
        dto.setUsername(request.getUsername());
        dto.setIsFirstTimeLogin(Boolean.FALSE);

        Account account = mapper.toEntity(dto);
        hydrateRelations(account, dto);

        byte[] salt = passwordService.generateSalt();
        byte[] hash = passwordService.hashPassword(request.getPassword(), salt);
        account.setPasswordSalt(salt);
        account.setPassword(hash);
        account.setPasswordResetKey(null);

        return mapper.toDto(accountRepository.save(account));
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        Account account = accountRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        boolean valid = passwordService.matches(
                request.getPassword(),
                account.getPasswordSalt(),
                account.getPassword()
        );

        if (!valid) {
            throw new RuntimeException("Invalid username or password");
        }

        return buildLoginResponse(account);
    }

    @Override
    public LoginResponse refreshToken(RefreshTokenRequest request) {
        var decoded = jwtService.verify(request.getRefreshToken());
        String tokenUse = decoded.getClaim("token_use").asString();
        if (!"refresh".equals(tokenUse)) {
            throw new RuntimeException("Invalid refresh token");
        }

        Long accountId;
        try {
            accountId = Long.parseLong(decoded.getSubject());
        } catch (Exception ex) {
            throw new RuntimeException("Invalid refresh token");
        }

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        return buildLoginResponse(account);
    }

    @Override
    public void changePassword(ChangePasswordRequest request) {
        Account account = accountRepository.findById(request.getAccountId())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        boolean valid = passwordService.matches(
                request.getCurrentPassword(),
                account.getPasswordSalt(),
                account.getPassword()
        );
        if (!valid) {
            throw new RuntimeException("Current password is incorrect");
        }

        byte[] salt = passwordService.generateSalt();
        byte[] hash = passwordService.hashPassword(request.getNewPassword(), salt);
        account.setPasswordSalt(salt);
        account.setPassword(hash);
        account.setPasswordResetKey(null);
        accountRepository.save(account);
    }

    @Override
    public ForgotPasswordResponse forgotPassword(ForgotPasswordRequest request) {
        Account account = accountRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        UUID resetKey = UUID.randomUUID();
        account.setPasswordResetKey(resetKey);
        accountRepository.save(account);

        return new ForgotPasswordResponse(resetKey.toString());
    }

    @Override
    public void resetPassword(ResetPasswordRequest request) {
        UUID resetKey;
        try {
            resetKey = UUID.fromString(request.getResetKey());
        } catch (Exception ex) {
            throw new RuntimeException("Invalid reset key");
        }

        Account account = accountRepository.findByPasswordResetKey(resetKey)
                .orElseThrow(() -> new RuntimeException("Invalid reset key"));

        byte[] salt = passwordService.generateSalt();
        byte[] hash = passwordService.hashPassword(request.getNewPassword(), salt);
        account.setPasswordSalt(salt);
        account.setPassword(hash);
        account.setPasswordResetKey(null);
        accountRepository.save(account);
    }

    @Override
    public Map<String, Object> getSecurityIdentities(SecurityIdentitiesRequest request) {
        var decoded = jwtService.verify(request.getToken());
        return Map.of(
                "subject", decoded.getSubject(),
                "username", decoded.getClaim("username").asString(),
                "email", decoded.getClaim("email").asString(),
                "role", decoded.getClaim("role").asString(),
                "organizationId", decoded.getClaim("organization_id").asLong(),
                "companyId", decoded.getClaim("company_id").asLong(),
                "tokenUse", decoded.getClaim("token_use").asString()
        );
    }

    @Override
    public void delete(Long id) {
        getById(id);
        accountRepository.deleteById(id);
    }

    private void hydrateRelations(Account account, AccountDto dto) {
        Role role = roleRepository.findById(dto.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role not found"));
        Organisation organisation = organisationRepository.findById(dto.getOrganisationId())
                .orElseThrow(() -> new RuntimeException("Organisation not found"));

        account.setRole(role);
        account.setOrganisation(organisation);

        if (dto.getCompanyId() != null) {
            Company company = companyRepository.findById(dto.getCompanyId())
                    .orElseThrow(() -> new RuntimeException("Company not found"));
            account.setCompany(company);
        } else {
            account.setCompany(null);
        }
    }

    private LoginResponse buildLoginResponse(Account account) {
        return LoginResponse.builder()
                .tokenType("Bearer")
                .accessToken(jwtService.generateAccessToken(account))
                .refreshToken(jwtService.generateRefreshToken(account))
                .idToken(jwtService.generateIdToken(account))
                .expiresIn(jwtService.getAccessTokenExpirySeconds())
                .account(mapper.toDto(account))
                .securityIdentities(List.of(
                        "username:" + account.getUsername(),
                        "role:" + (account.getRole() != null ? account.getRole().getCode() : ""),
                        "organizationId:" + (account.getOrganisation() != null ? account.getOrganisation().getId() : null),
                        "companyId:" + (account.getCompany() != null ? account.getCompany().getId() : null)
                ))
                .build();
    }
}
