package com.example.complex.validator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ComplexValidatorTest {

    private final ComplexValidator validator = new ComplexValidator();

    @Test
    void denominator() {
        assertTrue(validator.isValidDenominator(1));
        assertTrue(validator.isValidDenominator(-3));
        assertFalse(validator.isValidDenominator(0));
    }
}
