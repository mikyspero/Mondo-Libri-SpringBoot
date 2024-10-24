package com.generation.mondolibri.exceptions.checked;

public class NotEnoughStockException extends Exception {
    public NotEnoughStockException(String message) {
        super(message);
    }
}
