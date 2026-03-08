package com.ds.sqs.taxation.security.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.ds.sqs.taxation.security.entity.Account;

public interface JwtService {

    String generateAccessToken(Account account);

    String generateRefreshToken(Account account);

    String generateIdToken(Account account);

    DecodedJWT verify(String token);

    long getAccessTokenExpirySeconds();
}
