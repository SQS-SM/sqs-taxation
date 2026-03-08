package com.ds.sqs.taxation.security.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.ds.sqs.taxation.security.entity.Account;
import com.ds.sqs.taxation.security.service.JwtService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService {

    private final Algorithm algorithm;
    private final JWTVerifier verifier;
    private final String issuer;
    private final long accessTokenExpirySeconds;
    private final long refreshTokenExpirySeconds;
    private final long idTokenExpirySeconds;

    public JwtServiceImpl(
            @Value("${security.jwt.secret:change-me-local-dev-secret-change-me}") String secret,
            @Value("${security.jwt.issuer:sqs-taxation}") String issuer,
            @Value("${security.jwt.access-token-expiry-seconds:900}") long accessTokenExpirySeconds,
            @Value("${security.jwt.refresh-token-expiry-seconds:604800}") long refreshTokenExpirySeconds,
            @Value("${security.jwt.id-token-expiry-seconds:900}") long idTokenExpirySeconds
    ) {
        this.algorithm = Algorithm.HMAC256(secret);
        this.verifier = JWT.require(algorithm).withIssuer(issuer).build();
        this.issuer = issuer;
        this.accessTokenExpirySeconds = accessTokenExpirySeconds;
        this.refreshTokenExpirySeconds = refreshTokenExpirySeconds;
        this.idTokenExpirySeconds = idTokenExpirySeconds;
    }

    @Override
    public String generateAccessToken(Account account) {
        Instant now = Instant.now();
        return JWT.create()
                .withIssuer(issuer)
                .withSubject(String.valueOf(account.getId()))
                .withIssuedAt(Date.from(now))
                .withExpiresAt(Date.from(now.plusSeconds(accessTokenExpirySeconds)))
                .withClaim("token_use", "access")
                .withClaim("username", account.getUsername())
                .withClaim("email", account.getEmailAddress())
                .withClaim("role", account.getRole() != null ? account.getRole().getCode() : null)
                .withClaim("organization_id", account.getOrganisation() != null ? account.getOrganisation().getId() : null)
                .withClaim("company_id", account.getCompany() != null ? account.getCompany().getId() : null)
                .sign(algorithm);
    }

    @Override
    public String generateRefreshToken(Account account) {
        Instant now = Instant.now();
        return JWT.create()
                .withIssuer(issuer)
                .withSubject(String.valueOf(account.getId()))
                .withIssuedAt(Date.from(now))
                .withExpiresAt(Date.from(now.plusSeconds(refreshTokenExpirySeconds)))
                .withClaim("token_use", "refresh")
                .withClaim("username", account.getUsername())
                .sign(algorithm);
    }

    @Override
    public String generateIdToken(Account account) {
        Instant now = Instant.now();
        return JWT.create()
                .withIssuer(issuer)
                .withSubject(String.valueOf(account.getId()))
                .withIssuedAt(Date.from(now))
                .withExpiresAt(Date.from(now.plusSeconds(idTokenExpirySeconds)))
                .withClaim("token_use", "id")
                .withClaim("preferred_username", account.getUsername())
                .withClaim("given_name", account.getFirstName())
                .withClaim("family_name", account.getLastName())
                .withClaim("email", account.getEmailAddress())
                .withClaim("organization_id", account.getOrganisation() != null ? account.getOrganisation().getId() : null)
                .withClaim("company_id", account.getCompany() != null ? account.getCompany().getId() : null)
                .sign(algorithm);
    }

    @Override
    public DecodedJWT verify(String token) {
        return verifier.verify(token);
    }

    @Override
    public long getAccessTokenExpirySeconds() {
        return accessTokenExpirySeconds;
    }
}
