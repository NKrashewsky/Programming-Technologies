package com.example.gradebook.validator;

/**
 * Проверка параметров зачётной книжки.
 */
public class GradebookValidator {

    private static final int MIN_SEMESTER = 1;
    private static final int MAX_SEMESTER = 12;
    private static final int MIN_GRADE = 2;
    private static final int MAX_GRADE = 5;

    public boolean isValidText(String text) {
        return text != null && !text.isBlank();
    }

    public boolean isValidSemester(int semester) {
        return semester >= MIN_SEMESTER && semester <= MAX_SEMESTER;
    }

    public boolean isValidGrade(int grade) {
        return grade >= MIN_GRADE && grade <= MAX_GRADE;
    }
}
