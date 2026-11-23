package com.example.shop.exception;

/**
 * Некорректные данные для создания товара.
 */
public class InvalidProductDataException extends Exception {

    private static final long serialVersionUID = 1L;

    public InvalidProductDataException(String message) {
        super(message);
    }
}
