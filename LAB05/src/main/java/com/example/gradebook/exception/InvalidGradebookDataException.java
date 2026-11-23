package com.example.gradebook.exception;

/**
 * Некорректные данные для создания объектов зачётной книжки.
 */
public class InvalidGradebookDataException extends Exception {

    private static final long serialVersionUID = 1L;

    public InvalidGradebookDataException(String message) {
        super(message);
    }
}
