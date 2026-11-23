package com.example.complex.exception;

/**
 * Некорректные данные для создания комплексного числа.
 */
public class InvalidComplexDataException extends Exception {

    private static final long serialVersionUID = 1L;

    public InvalidComplexDataException(String message) {
        super(message);
    }
}
