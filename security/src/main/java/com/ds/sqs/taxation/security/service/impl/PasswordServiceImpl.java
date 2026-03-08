package com.ds.sqs.taxation.security.service.impl;

import com.ds.sqs.taxation.security.service.PasswordService;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.util.Arrays;

@Service
public class PasswordServiceImpl implements PasswordService {

    private static final int SALT_SIZE = 16;
    private static final int ITERATIONS = 65_536;
    private static final int KEY_LENGTH = 256;
    private static final String ALGORITHM = "PBKDF2WithHmacSHA256";

    @Override
    public byte[] generateSalt() {
        byte[] salt = new byte[SALT_SIZE];
        new SecureRandom().nextBytes(salt);
        return salt;
    }

    @Override
    public byte[] hashPassword(String rawPassword, byte[] salt) {
        if (rawPassword == null || rawPassword.isBlank()) {
            throw new RuntimeException("Password is required");
        }
        try {
            PBEKeySpec spec = new PBEKeySpec(rawPassword.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
            SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);
            return factory.generateSecret(spec).getEncoded();
        } catch (Exception ex) {
            throw new RuntimeException("Unable to hash password", ex);
        }
    }

    @Override
    public boolean matches(String rawPassword, byte[] salt, byte[] expectedHash) {
        if (salt == null || expectedHash == null) {
            return false;
        }
        byte[] computed = hashPassword(rawPassword, salt);
        return Arrays.equals(computed, expectedHash);
    }
}
