package com.generation.mondolibri.exceptions.checked;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) {
        super(message);
    }
}
