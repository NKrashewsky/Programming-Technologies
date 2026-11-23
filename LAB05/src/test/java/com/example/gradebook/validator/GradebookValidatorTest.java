package com.example.gradebook.validator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class GradebookValidatorTest {

    private final GradebookValidator validator = new GradebookValidator();

    @Test
    void text() {
        assertTrue(validator.isValidText("Math"));
        assertFalse(validator.isValidText(" "));
        assertFalse(validator.isValidText(null));
    }

    @Test
    void semester() {
        assertTrue(validator.isValidSemester(1));
        assertTrue(validator.isValidSemester(12));
        assertFalse(validator.isValidSemester(0));
        assertFalse(validator.isValidSemester(13));
    }

    @Test
    void grade() {
        assertTrue(validator.isValidGrade(2));
        assertTrue(validator.isValidGrade(5));
        assertFalse(validator.isValidGrade(1));
        assertFalse(validator.isValidGrade(6));
    }
}
