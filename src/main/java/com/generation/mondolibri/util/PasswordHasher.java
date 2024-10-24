package com.generation.mondolibri.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Utility class for hashing passwords using SHA-256 algorithm.
 * Provides methods to hash passwords and compare a plain text password
 * with a hashed password.
 */
public class PasswordHasher {

    // Static initialization block to load the MessageDigest algorithm
    static {
        try {
            // Ensure that SHA-256 algorithm is available
            MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            // If SHA-256 algorithm is not found, throw an ExceptionInInitializerError
            throw new ExceptionInInitializerError("SHA-256 algorithm not found");
        }
    }

    /**
     * Hashes a plain text password using SHA-256 algorithm.
     * @param password The plain text password to be hashed.
     * @return A hexadecimal string representing the hashed password.
     * @throws NoSuchAlgorithmException If the SHA-256 algorithm is not available.
     */
    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashedBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));

        // Convert byte array to hexadecimal string representation
        StringBuilder sb = new StringBuilder();
        for (byte b : hashedBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    /**
     * Compares a plain text password with a hashed password.
     * @param password The plain text password to compare.
     * @param hashedPassword The hashed password stored in the database.
     * @return True if the plain text password matches the hashed password, false otherwise.
     * @throws NoSuchAlgorithmException If the SHA-256 algorithm is not available.
     */
    public static boolean comparePassword(String password, String hashedPassword) throws NoSuchAlgorithmException {
        // Hash the provided password and compare it with the hashed password from the database
        String passwordHash = hashPassword(password);
        return hashPassword(password).equals(hashedPassword);

    }
}
