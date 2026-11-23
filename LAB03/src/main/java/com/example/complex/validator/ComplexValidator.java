package com.example.complex.validator;

/**
 * Проверка параметров дроби.
 */
public class ComplexValidator {

    public boolean isValidDenominator(long denominator) {
        return denominator != 0;
    }
}
