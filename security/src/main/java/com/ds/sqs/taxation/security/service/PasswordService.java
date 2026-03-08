package com.ds.sqs.taxation.security.service;

public interface PasswordService {

    byte[] generateSalt();

    byte[] hashPassword(String rawPassword, byte[] salt);

    boolean matches(String rawPassword, byte[] salt, byte[] expectedHash);
}
