package com.example.gradebook.factory;

import com.example.gradebook.entity.Assessment;
import com.example.gradebook.exception.InvalidGradebookDataException;
import com.example.gradebook.validator.GradebookValidator;

/**
 * Creator из паттерна Factory Method.
 * Формат строки: TYPE;semester;subject;value
 */
public abstract class AssessmentCreator {

    protected static final int SUBJECT_INDEX = 2;
    protected static final int VALUE_INDEX = 3;

    private static final int REQUIRED_PARAMS = 4;

    protected final GradebookValidator validator = new GradebookValidator();

    /**
     * Фабричный метод.
     *
     * @param params параметры строки файла
     * @return экзамен или зачёт
     * @throws InvalidGradebookDataException если данные нельзя исправить
     */
    public abstract Assessment createAssessment(String[] params)
            throws InvalidGradebookDataException;

    protected void checkLength(String[] params) throws InvalidGradebookDataException {
        if (params.length < REQUIRED_PARAMS) {
            throw new InvalidGradebookDataException("Expected " + REQUIRED_PARAMS
                    + " parameters but got " + params.length);
        }
    }

    protected String parseSubject(String raw) throws InvalidGradebookDataException {
        if (!validator.isValidText(raw)) {
            throw new InvalidGradebookDataException("Subject is empty");
        }
        return raw;
    }
}
