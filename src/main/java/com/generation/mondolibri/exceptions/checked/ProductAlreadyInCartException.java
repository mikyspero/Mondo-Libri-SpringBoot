package com.generation.mondolibri.exceptions.checked;

public class ProductAlreadyInCartException extends Exception {
    public ProductAlreadyInCartException(String message) {
        super(message);
    }
}
